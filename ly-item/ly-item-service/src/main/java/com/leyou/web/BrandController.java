package com.leyou.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface BrandController {

    ResponseEntity<Pagination> queryPageBrnads(Pagination pagination);

    @GetMapping("list1")
    ResponseEntity<PageResult<Brand>> queryPageBrnads1(@RequestParam(name = "search", required = false) String search,
                                                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(name = "rowsPerPage", defaultValue = "5") Integer rowsPerPage,
                                                       @RequestParam(name = "sortBy", required = false) String sortBy,
                                                       @RequestParam(name = "descending", defaultValue = "false") Boolean descending);
}
