package com.leyou.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    List<Category> queryCategoryListByPid(Long pid);
}
