package com.leyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.ov.SpuBo;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.Stock;
import com.leyou.mapper.SkuMapper;
import com.leyou.mapper.SpuDetailMapper;
import com.leyou.mapper.SpuMapper;
import com.leyou.mapper.StockMapper;
import com.leyou.service.BrandService;
import com.leyou.service.CategoryService;
import com.leyou.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodsService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult<Spu> queryForPage(String key, Integer page, Integer rows, String sortBy, Boolean desc, Boolean saleable) {
        PageResult<Spu> pageResult = new PageResult<>();
        PageHelper.startPage(page, rows);
        //开启查询
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(key)) {
            criteria.andLike("title","%"+key+"%");
        }
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        example.setOrderByClause(sortBy + (desc ? " desc " : " asc "));
        List<Spu> spuList = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new LyException(ExceptionsEnums.CANNOT_FIND_SPU);
        }
        //向spuList中添加cname和bname
        addCNameAndBName(spuList);

        //2、根据bidList进行查询
        pageResult.setItems(spuList);
        pageResult.setTotal(new PageInfo<>(spuList).getTotal());
        return pageResult;
    }

    private void addCNameAndBName(List<Spu> spuList) {
        for (Spu spu : spuList) {
            //1 查询cname
            List<String> nameList = categoryService.queryCategoryByCids(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            String s = StringUtils.join(nameList, "/");
            spu.setCname(s);
            //2查询BName进行封装
            spu.setBname(brandService.queryBrandByBid(spu.getBrandId()).getName());
        }
    }

    @Override
    public void saveGoods(SpuBo spuBo) {
        //保存商品，保存Spu SpuDetail Sku Stock四组数据
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(new Date());
        //保存spu信息
        int i = spuMapper.insert(spuBo);
        if (i!=1){
            throw new LyException(ExceptionsEnums.INSERT_SPU_ERROR);
        }
        //保存spu详情
        spuBo.getSpuDetail().setSpuId(spuBo.getId());
        i = spuDetailMapper.insert(spuBo.getSpuDetail());
        if (i!=1){
            throw new LyException(ExceptionsEnums.INSERT_SPUDETAIL_ERROR);
        }
        //保存sku和stock
        saveSkuAndStock(spuBo.getSkus(),spuBo.getId());
    }

    private void saveSkuAndStock(List<Sku> skus, Long id) {
        for (Sku sku :skus){
            if (!sku.getEnable()){
                continue;
            }
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(new Date());
            sku.setSpuId(id);
            int i = skuMapper.insert(sku);
            if (i!=1){
                throw new LyException(ExceptionsEnums.INSERT_SKULIST_ERROR);
            }
            //保存库存信息
            Stock stock=new Stock();
            stock.setStock(sku.getStock().getStock());
            stock.setSkuId(sku.getId());
            System.out.println("stock-------->"+stock);
            System.out.println("sku-------->"+sku);
             i = stockMapper.insert(stock);
            if (i!=1){
                throw new LyException(ExceptionsEnums.INSERT_STOCK_ERROR);
            }
        }

    }
}
