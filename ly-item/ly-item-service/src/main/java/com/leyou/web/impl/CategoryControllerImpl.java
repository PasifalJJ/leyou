package com.leyou.web.impl;

import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Category;
import com.leyou.service.CategoryService;
import com.leyou.web.CategoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryControllerImpl implements CategoryController {
    @Autowired
    private CategoryService categoryservice;

    /**
     * 根据category的父pid查询分类
     *
     * @param pid
     * @return
     * @RequestParam接收参数的方式为但uri为 ?pid=10 的时候使用
     * @PathVariable接收参数的形式为rest风格使用 list/{pid}
     */
    @Override
    @GetMapping("list")
    public ResponseEntity<Category[]> queryCategoryListByPid(@RequestParam("pid") Long pid) {
        List<Category> categories = categoryservice.queryCategoryListByPid(pid);
        return ResponseEntity.ok(categories.toArray(new Category[categories.size()]));
    }

    /**
     * 根据商品id查询分类
     * @param bid
     * @return
     */
    @Override
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid) {
        return ResponseEntity.ok(categoryservice.queryCategoryByBid(bid));
    }

    /**
     * 根据cid的集合，查询分类集合
     * @param ids
     * @return
     */
    @Override
    @GetMapping("names")
    public ResponseEntity<List<Category>> queryCategoryCyBids(@RequestParam("ids") List<Long> ids){
        if (ids==null || ids.size()<1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryservice.queryCategoryByCids(ids));
    }
}
