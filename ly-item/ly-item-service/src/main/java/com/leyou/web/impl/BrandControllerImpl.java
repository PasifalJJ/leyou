package com.leyou.web.impl;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Pagination;
import com.leyou.service.BrandService;
import com.leyou.web.BrandController;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandControllerImpl implements BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询当前页信息new 使用分页助手进行查询
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    @Override
    @GetMapping("list")
    public ResponseEntity<PageResult<Brand>> queryPageBrnads(@RequestParam(name = "search",required = false) String search,
                                                                    @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                                    @RequestParam(name = "rowsPerPage", defaultValue = "5") Integer rowsPerPage,
                                                                    @RequestParam(name = "sortBy" ,required = false) String sortBy,
                                                                    @RequestParam(name = "descending" ,defaultValue = "false") Boolean descending) {

        return ResponseEntity.ok(brandService.queryPageBrnads(search, page, rowsPerPage, sortBy, descending));
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @Override
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand){
        brandService.saveBrand(brand,brand.getCategories());
        System.out.println("brand = " + brand);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@RequestParam("id") Integer id){
        if (id==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
