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

    /**
     * 根据parentId查找所有category
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        List<Category> categories = categoryMapper.queryCategoryListByPid(pid);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionsEnums.CATEGORY_CANNOT_BE_NULL);
        }
        return categories;
    }

    /**
     * 根据商品id查询分类
     * @param bid
     * @return
     */
    @Override
    public List<Category> queryCategoryByBid(Long bid) {
        Category category=new Category();
        category.setId(bid);
        List<Category> categories = categoryMapper.queryCategoryByBid(bid);
        if (categories == null){
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        return categories;
    }

    @Override
    public List<Category> queryCategoryByCids(List<Long> cids){
        List<Category> categories = categoryMapper.selectByIdList(cids);
        if (CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
        }
        return categories;
    }
}
