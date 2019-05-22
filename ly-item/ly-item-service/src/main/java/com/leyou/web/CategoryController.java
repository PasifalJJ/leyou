package com.leyou.web;

import com.leyou.item.pojo.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {
    ResponseEntity<List<Category>> queryCategoryListByPid(Long pid);

}
