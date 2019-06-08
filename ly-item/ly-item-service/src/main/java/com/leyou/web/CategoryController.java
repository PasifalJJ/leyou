package com.leyou.web;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 根据cid集合查询分类集合
     * @param ids
     * @return
     */
    ResponseEntity<List<Category>> queryCategoryCyBids(List<Long> ids);
}
