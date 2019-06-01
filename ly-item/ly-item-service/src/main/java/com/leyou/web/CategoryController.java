package com.leyou.web;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {
    /**
     * 根据parentId查询分类
     * @param pid
     * @return
     */
    ResponseEntity<Category[]> queryCategoryListByPid(Long pid);

    /**
     * 根据商品id查询分类
     * @param bid
     * @return
     */
    ResponseEntity<List<Category>> queryCategoryByBid (Long bid);

}
