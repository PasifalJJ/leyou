package com.leyou.web;

import com.github.pagehelper.Page;
import com.leyou.common.vo.PageResult;
import com.leyou.item.ov.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsController {
    /**
     * 根据条件查询分页
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param saleable
     * @return
     */
    ResponseEntity<PageResult<Spu>> queryForPage(@RequestParam(name = "key", required = false) String key,
                                                 @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                                 @RequestParam(name = "rows", required = true, defaultValue = "5") Integer rows,
                                                 @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(name = "desc", required = false, defaultValue = "false") Boolean desc,
                                                 @RequestParam(name = "saleable", required = false) Boolean saleable);

    /**
     * 保存商品信息
     *
     * @param spuBo
     * @return
     */
    ResponseEntity<Void> saveGoods(SpuBo spuBo);

    /**
     * 根据id查找spu详情
     *
     * @param id
     * @return
     */
    ResponseEntity<SpuDetail> querySpuDetailById(Long id);

    /**
     * 根绝spu id查找skus
     * @param id
     * @return
     */
    ResponseEntity<List<Sku>> querySkuBySpuId(Long id);
}
