package com.leyou.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand>, SelectByIdsMapper<String> {

    /**
     * 新增category和brand表的对应关系
     * @param category
     * @param bid
     * @return
     */
    @Insert("insert into tb_category_brand (category_id,brand_id) values (#{category},#{bid})")
    Integer insertCategoryBrand(@Param("category") Long category, @Param("bid") Long bid);

    @Select("select t2.id,t2.name " +
            "from " +
                "tb_category_brand t1 inner join tb_brand t2 " +
            "on " +
                "t1.brand_id = t2.id " +
            "where " +
                "t1.category_id = #{cid}")
    List<Brand> queryBrandByCid(@Param("cid") Long cid);

}
