package com.leyou.item.api;

import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.ov.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface GoodsApi {
    /**
     * 根据条件查询分页
     *
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<Spu>> queryForPage(@RequestParam(name = "key",required = false) String key,
                                                        @RequestParam(name ="page",required = true,defaultValue = "1") Integer page,
                                                        @RequestParam(name ="rows",required = true,defaultValue = "5") Integer rows,
                                                        @RequestParam(name ="sortBy",required = false,defaultValue = "id") String sortBy,
                                                        @RequestParam(name ="desc",required =false,defaultValue = "false") Boolean desc,
                                                        @RequestParam(name ="saleable",required = false) Boolean saleable) ;

    /**
     * 保存商品信息
     * @param spuBo
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo);

    /**
     * 根据spu的id查询商品详情
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailById(@PathVariable("id") Long id);

    /**
     * 根据spuid查询sku集合
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long id);

}
