package com.leyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Pagination;
import com.leyou.mapper.BrandMapper;
import com.leyou.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Pagination queryPageBrnads(Pagination pagination) {
        List<Brand> brands = brandMapper.queryPageBrnads(pagination);
        Long totalBrands = (long) brandMapper.selectCount(null);
        pagination.setBrands(brands);
        pagination.setTotalBrands(totalBrands);
        return pagination;
    }

    @Override
    public PageResult<Brand> queryPageBrnads1(
                                    String search, Integer page, Integer rowsPerPage,
                                    String sortBy, Boolean descending) {
        //1、分页，使用分页组件进行分页,输入当前页面和每页显示行数
        PageHelper.startPage(page,rowsPerPage); //使用拦截器原理
        //2、过滤、使用Example过滤的通用Mapper过滤方法
        Example example=new Example(Brand.class);
        if (StringUtils.isNoneBlank(search)){
            example.createCriteria().orLike("name","%"+search+"%").
                    orEqualTo(search.toUpperCase());
        }
        //3、排序
        if (StringUtils.isNotBlank(sortBy)){
            String orderByClause=sortBy+(descending?" DESC ":" ASC ");
            example.setOrderByClause(orderByClause);
        }
        //4、查询
        //用PageHelper查询最后会返回一个Page对象，里面包含总页数和总记录数各种信息
        List<Brand> brandList = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brandList)){
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        //使用PageHelper提供的PageInf对象可以将list转为pageinfo对象，获取其中的数据
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        return new PageResult<Brand>(brandPageInfo.getTotal(),brandList);
    }

    /**
     * 保存品牌信息
     * @param brand
     * @param categories
     */
    @Transactional
    @Override
    public void saveBrand(Brand brand, List<Integer> categories) {
        //新增品牌信息
        brandMapper.insertSelective(brand);
        //新增品牌和分类的中间表
        for (Integer category : categories) {
            Integer integer = brandMapper.insertCategoryBrand(category, brand.getId());
            if (integer!=1){
                throw new LyException(null);    //增加失败触发事务回滚
            }
        }
    }
}
