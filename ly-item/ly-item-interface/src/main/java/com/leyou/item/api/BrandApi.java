package com.leyou.item.api;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BrandApi {

    /**
     * 查询每页显示信息或根据条件显示每页查询信息
     * @param search
     * @param page
     * @param rowsPerPage
     * @param sortBy
     * @param descending
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<PageResult<Brand>> queryPageBrnads(@RequestParam(name = "search",required = false) String search,
                                                             @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                             @RequestParam(name = "rowsPerPage", defaultValue = "5") Integer rowsPerPage,
                                                             @RequestParam(name = "sortBy" ,required = false) String sortBy,
                                                             @RequestParam(name = "descending" ,defaultValue = "false") Boolean descending) ;

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveOrUpdateBrand(Brand brand);

    /**
     * 根据bid删除商品
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@RequestParam("id") Long id);

    @GetMapping("bid/{bid}")
    public ResponseEntity<Brand> queryBrandByBid(@PathVariable("bid") Long bid);

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable Long cid);

}
