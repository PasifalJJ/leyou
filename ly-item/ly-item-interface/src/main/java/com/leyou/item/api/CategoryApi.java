package com.leyou.item.api;

import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {
    /**
     * 根据category的父pid查询分类
     *
     * @param pid
     * @return
     * @RequestParam接收参数的方式为但uri为 ?pid=10 的时候使用
     * @PathVariable接收参数的形式为rest风格使用 list/{pid}
     */
    @GetMapping("list")
    public ResponseEntity<Category[]> queryCategoryListByPid(@RequestParam("pid") Long pid) ;

    /**
     * 根据商品id查询分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid) ;

    /**
     * 根据cid的集合，查询分类集合
     * @param ids
     * @return
     */
    @GetMapping("names")
    public ResponseEntity<List<Category>> queryCategoryCyBids(@RequestParam("ids") List<Long> ids);

}
