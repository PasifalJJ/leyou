package com.leyou.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 根据bid查询商品
     * @param pid
     * @return
     */
    List<Category> queryCategoryListByPid( Long pid);

    /**
     * 根据商品id查询分类
     * @param bid
     * @return
     */
    List<Category> queryCategoryByBid(Long bid);

    List<Category> queryCategoryByCids(List<Long> cids);

}
