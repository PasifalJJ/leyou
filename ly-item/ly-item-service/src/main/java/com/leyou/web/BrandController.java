package com.leyou.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandController {

    /**
     * 查询每页显示信息或根据条件显示每页查询信息
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    ResponseEntity<PageResult<Brand>> queryPageBrnads(@RequestParam(name = "search", required = false) String search,
                                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(name = "rowsPerPage", defaultValue = "5") Integer rowsPerPage,
                                                       @RequestParam(name = "sortBy", required = false) String sortBy,
                                                       @RequestParam(name = "descending", defaultValue = "false") Boolean descending);


    /**
     * 保存或者更新用户信息
     * @param brand
     * @return
     */
    ResponseEntity<Void> saveOrUpdateBrand(Brand brand);

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @DeleteMapping
    ResponseEntity<Void> deleteBrand(Long id);

    /**
     * 根据bid查询商品
     * @param bid
     * @return
     */
    ResponseEntity<Brand> queryBrandByBid(Long bid);

    @GetMapping("cid/{cid}")
    ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable Long cid);
}
