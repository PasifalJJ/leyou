package com.leyou.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {


    @Select("select * from tb_category where parent_id = #{pid}")
    @Results(id = "queryCategoryListByPid",value = {
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "isParent",column = "is_parent"),
            @Result(property = "sort",column = "sort"),
    })
    List<Category> queryCategoryListByPid(Long pid);


    /**
     * 根据bid查询分类
     * @param bid
     * @return
     */
    @Select("select * from tb_category_brand tcb,tb_category tc where tc.id = #{bid} ")
    Category queryCategoryByBid(@Param("bid") Long bid);
}
