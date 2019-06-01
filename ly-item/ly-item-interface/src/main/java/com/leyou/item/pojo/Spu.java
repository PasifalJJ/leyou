package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_spu")
public class Spu implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;       //标题
    private String subTitle;    //子标题
    private Long cid1;       //一级分类id
    private Long cid2;      //二级分类id
    private Long cid3;      //三级分类id
    private Long brandId;   //品牌id
    private Boolean saleable;   //是否上架
    private Boolean valid;  //是否有效
    @Column(name = "create_time")
    private Date createTime;    //创建时间

    @Column(name = "last_update_time")
    private Date lastUpdateTime;    //最后修改时间
    @Transient
    private String cname;
    @Transient
    private String bname;
}