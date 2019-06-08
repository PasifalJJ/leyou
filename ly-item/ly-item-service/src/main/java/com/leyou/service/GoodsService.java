package com.leyou.service;

import com.leyou.common.vo.PageResult;
import com.leyou.item.ov.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

public interface GoodsService {
    /**
     * 查询商品分页
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param saleable
     * @return
     */
    PageResult<Spu> queryForPage(String key, Integer page, Integer rows, String sortBy, Boolean desc, Boolean saleable);

    /**
     * 保存商品信息
     * @param spuBo
     */
    void saveGoods(SpuBo spuBo);

    SpuDetail querySpuDetailById(Long id);

    List<Sku> querySkuBySpuId(Long id);
}
