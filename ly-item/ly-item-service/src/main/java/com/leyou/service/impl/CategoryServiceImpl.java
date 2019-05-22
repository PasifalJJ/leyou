package com.leyou.service.impl;

import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Category;
import com.leyou.mapper.CategoryMapper;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        List<Category> categories = categoryMapper.queryCategoryListByPid(pid);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionsEnums.CATEGORY_CANNOT_BE_NULL);
        }
        return categories;
    }
}
