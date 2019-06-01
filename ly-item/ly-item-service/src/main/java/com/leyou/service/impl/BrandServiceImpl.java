package com.leyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.CategoryAndBrand;
import com.leyou.mapper.BrandMapper;
import com.leyou.mapper.CategoryMapper;
import com.leyou.mapper.CategoryAndBrandMapper;
import com.leyou.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryAndBrandMapper categoryAndBrandMapper;
    /**
     * 根据条件查询分页
     *
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    @Override
    public PageResult<Brand> queryPageBrnads(
            String search, Integer page, Integer rowsPerPage,
            String sortBy, Boolean descending) {
        //1、分页，使用分页组件进行分页,输入当前页面和每页显示行数
        PageHelper.startPage(page, rowsPerPage); //使用拦截器原理
        //2、过滤、使用Example过滤的通用Mapper过滤方法
        Example example = new Example(Brand.class);
        if (StringUtils.isNoneBlank(search)) {
            example.createCriteria().orEqualTo("letter", search.toUpperCase()).
                    orLike("name", "%" + search + "%");
        }
        //3、排序
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (descending ? " DESC " : " ASC ");
            example.setOrderByClause(orderByClause);
        }
        //4、查询
        //用PageHelper查询最后会返回一个Page对象，里面包含总页数和总记录数各种信息
        List<Brand> brandList = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brandList)) {
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        //使用PageHelper提供的PageInf对象可以将list转为pageinfo对象，获取其中的数据
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        return new PageResult<Brand>(brandPageInfo.getTotal(), brandList);
    }

    /**
     * 保存品牌信息
     *
     * @param brand
     * @param categories
     */
    @Transactional(rollbackFor = LyException.class)
    @Override
    public void saveBrand(Brand brand, List<Long> categories) {
        //查询逐渐id是否存在，不存在则保存，存在则进行更新
        boolean b = brandMapper.existsWithPrimaryKey(brand.getId());
        if (b){
            //更新数据
            updateBrand(brand);
        }else {
            //新增品牌信息
            brandMapper.insertSelective(brand);
            //新增品牌和分类的中间表
            for (Long category : categories) {
                Integer integer = brandMapper.insertCategoryBrand(category, brand.getId());
                if (integer != 1) {
                    throw new LyException(null);    //增加失败触发事务回滚
                }
            }
        }
    }

    /**
     * 根据id删除商品
     *
     * @param id
     */
    @Override
    public void deleteBrand(Long id) {
        int i = brandMapper.deleteByPrimaryKey(id);
        if (i != 1) {
            throw new LyException(ExceptionsEnums.DELETE_UNKNOWN_BRAND);
        }
        CategoryAndBrand categoryAndBrand = new CategoryAndBrand();
        categoryAndBrand.setBrandId(id);
        int delete = categoryAndBrandMapper.delete(categoryAndBrand);
        if (delete < 1) {
            throw new LyException(ExceptionsEnums.DELETE_UNKNOWN_BRAND);
        }
    }

    /**
     * 根据bid查询商品
     *
     * @param bid
     * @return
     */
    @Override
    public Brand queryBrandByBid(Long bid) {
        List<Category> categorys = categoryMapper.queryCategoryByBid(bid);

        List<Long> categoryIds = categorys.stream().map(Category::getId).collect(Collectors.toList());

        Brand brand = brandMapper.selectByPrimaryKey(bid);
        brand.setCategories(categoryIds);
        if (brand == null) {
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        return brand;
    }

    /**
     * 更新商品信息
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        int i = brandMapper.updateByPrimaryKeySelective(brand);
        if (i < 1) {
            throw new LyException(ExceptionsEnums.UPDATE_FILE_EXCEPTION);
        }
    }

    @Override
    public List<Brand> queryBrandByCid(Long cid) {
        //对tb_brand和tb_category_brand进行联查
        List<Brand> brands= brandMapper.queryBrandByCid(cid);
        if (brands==null){
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        return brands;
    }
}
