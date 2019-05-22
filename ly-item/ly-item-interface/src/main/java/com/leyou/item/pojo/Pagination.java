package com.leyou.item.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public  class Pagination<T> implements Serializable {
    private Integer page;
    private Integer rowsPerpage;
    private Boolean descending;
    private String sortBy;
    private Long totalBrands;
    private String search;
    private List<Brand> brands;

    public Integer getStart(){
        if (page!=null && rowsPerpage!=null){
            return (page-1)*rowsPerpage;
        }
        return 0;
    }
}
