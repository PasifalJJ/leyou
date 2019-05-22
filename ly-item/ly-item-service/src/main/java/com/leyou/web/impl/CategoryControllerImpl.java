package com.leyou.web.impl;

import com.leyou.item.pojo.Category;
import com.leyou.service.CategoryService;
import com.leyou.web.CategoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryControllerImpl implements CategoryController {
    @Autowired
    private CategoryService categoryservice;

    /**
     * 根据category的父pid查询分类
     * @RequestParam接收参数的方式为但uri为 ?pid=10 的时候使用
     * @PathVariable接收参数的形式为rest风格使用 list/{pid}
     * @param pid
     * @return
     */
    @Override
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid) {
        return ResponseEntity.ok(categoryservice.queryCategoryListByPid(pid));
    }
}
