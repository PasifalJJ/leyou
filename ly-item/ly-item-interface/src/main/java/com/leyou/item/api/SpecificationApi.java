package com.leyou.item.api;

import com.leyou.item.pojo.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface SpecificationApi {

    /**
     * 根据分类cid查找规格参数
     * @param cid
     * @return
     */
    @GetMapping("{cid}")
    public ResponseEntity<String> querySpecification(@PathVariable("cid") Long cid);

    /**
     * 保存商品规格
     * @param specification
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveSpecification(Specification specification);
    /**
     * 更新产品分类信息
     * @param specification
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateSpecification(Specification specification);


}
