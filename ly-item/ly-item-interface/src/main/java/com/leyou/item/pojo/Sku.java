package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String ownSpec;//商品特殊规格的键值对
    private String indexes;//商品特殊规格的下标
    private Boolean enable;//是否有效，逻辑删除使用
    private Date createTime;//创建时间
    private Date lastUpdateTime;//最后修改时间
    @Transient
    private Stock stock; //库存字段，在库数据库中的另一张表保存，方便查询。修改的时候一定要注意
}
