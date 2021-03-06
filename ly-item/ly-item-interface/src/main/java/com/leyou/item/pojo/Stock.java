package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_stock")
public class Stock implements Serializable {
    @Id
    @Column(name = "sku_id")
    private Long skuId;
    private Integer seckillStock;//秒杀可用库存
    private Integer seckillTotal;//已秒杀数量
    private Integer stock;//正常库存
}
