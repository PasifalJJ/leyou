package com.leyou.web;

import com.leyou.item.pojo.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface SpecificationController {

    /**
     * 根据分类cid查找所有规格参数
     * @param cid
     * @return
     */
    ResponseEntity<String> querySpecification(Long cid);

    /**
     * 保存商品规格
     * @param specification
     * @return
     */
    ResponseEntity<Void> saveSpecification(Specification specification);

    /**
     * 更新产品分类信息
     * @param specification
     * @return
     */
    ResponseEntity<Void> updateSpecification(Specification specification);
}
