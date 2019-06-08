package com.leyou.item.ov;

import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class SpuBo extends Spu  {
    @Transient
    private SpuDetail spuDetail;    //商品详情
    @Transient
    private List<Sku> skus; //sku列表
}
