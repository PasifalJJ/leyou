package com.leyou.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private Long total; //总条数
    private Long totalpages;    //总页数
    private List<T> items;  //当前页数据

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalpages, List<T> items) {
        this.total = total;
        this.totalpages = totalpages;
        this.items = items;
    }

    public PageResult() {
    }
}
