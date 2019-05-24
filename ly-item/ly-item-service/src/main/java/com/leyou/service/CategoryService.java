package com.leyou.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> queryCategoryListByPid( Long pid);


    Category queryCategoryByBid(Long bid);
}
