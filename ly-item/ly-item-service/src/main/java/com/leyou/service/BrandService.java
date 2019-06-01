package com.leyou.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 根据条件查询分页
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    PageResult<Brand> queryPageBrnads(
            String search, Integer page, Integer rowsPerPage,
            String sortBy, Boolean descending);

    /**
     * 新增品牌
     * @param brand
     * @param categories
     */
    void saveBrand(Brand brand, List<Long> categories);

    /**
     * 根据id产出商品
     * @param id
     */
    void deleteBrand(Long id);

    /**
     * 根据bid查询商品
     * @param bid
     * @return
     */
    Brand queryBrandByBid(Long bid);

    void updateBrand(Brand brand);

    List<Brand> queryBrandByCid(Long cid);
}
