package com.leyou.service;

import com.leyou.item.pojo.Specification;

public interface SpecificationService {
    /**
     * 根据分类id查询规格参数
     * @param cid
     * @return
     */
    String querySpecification(Long cid);

    /**
     * 保存商品规格
     * @param specification
     */
    void saveSpecification(Specification specification);

    /**
     * 更新产品分类信息
     * @param specification
     * @return
     */
    void updateSpecification(Specification specification);

}
