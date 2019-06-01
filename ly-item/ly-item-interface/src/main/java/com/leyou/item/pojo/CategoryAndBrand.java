package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_category_brand")
@Data
public class CategoryAndBrand implements Serializable {
    @Id
    private Long categoryId;
    @Id
    private Long brandId;
}
