package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
//javax.persistence中的注解，java持久化，通用mapper引用了这个依赖，指定对象和表的名称相匹配
@Table(name = "tb_category")
public class Category implements Serializable {
    @Id
    //使用 JDBC 方式获取主键，优先级最高
    @KeySql(useGeneratedKeys=true)
    private Long id;
    private String name;
    private Long parentId;
    private boolean isParent;
    private Integer sort;
}
