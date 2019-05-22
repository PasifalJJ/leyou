package com.leyou.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Pagination;

import java.util.List;

public interface BrandService {
    Pagination queryPageBrnads(Pagination pagination);

    /**
     * 查询当前页信息
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    PageResult<Brand> queryPageBrnads1( String search, Integer page, Integer rowsPerPage,
                                              String sortBy, Boolean descending);

    /**
     * 新增品牌
     * @param brand
     * @param categories
     */
    void saveBrand(Brand brand, List<Integer> categories);
}
