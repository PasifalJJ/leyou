package com.leyou.mapper;

import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Pagination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    List<Brand> queryPageBrnads(Pagination pagination);

    /**
     * 新增category和brand表的对应关系
     * @param category
     * @param bid
     * @return
     */
    @Insert("insert into tb_category_brand (category_id,brand_id) values (#{category},#{bid})")
    Integer insertCategoryBrand(@Param("category") Integer category, @Param("bid") Long bid);
}
