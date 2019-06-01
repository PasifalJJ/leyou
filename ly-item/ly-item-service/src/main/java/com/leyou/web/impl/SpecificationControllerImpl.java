package com.leyou.web.impl;

import com.leyou.item.pojo.Specification;
import com.leyou.service.SpecificationService;
import com.leyou.web.SpecificationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spec")
public class SpecificationControllerImpl implements SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类cid查找规格参数
     * @param cid
     * @return
     */
    @GetMapping("{cid}")
    public ResponseEntity<String> querySpecification(@PathVariable("cid") Long cid){
       return ResponseEntity.ok(specificationService.querySpecification(cid));
    }

    @PostMapping
    public ResponseEntity<Void> saveSpecification(Specification specification){
        specificationService.saveSpecification(specification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 更新产品分类信息
     * @param specification
     * @return
     */
    @Override
    @PutMapping
    public ResponseEntity<Void> updateSpecification(Specification specification){
        specificationService.updateSpecification(specification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
