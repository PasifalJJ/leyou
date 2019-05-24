package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionsEnums {
    //价格不能为空
    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    //商品信息不能为空
    NAME_CANNOT_BE_NULL(400,"商品信息不能为空"),
    //类别不能为空
    CATEGORY_CANNOT_BE_NULL(404,"类别不能为空"),
    //未找到商品
    CANNOT_FIND_BRAND(404,"未找到品牌"),
    DELETE_UNKNOWN_BRAND(404,"上传id异常"),
    //private static final ExceptionsEnums e=new ExceptionsEnums (400,"价格不能为空");
    FILE_UPLOAD_EXCEPTION(500,"文件保存异常"),
    UPLOAD_FILE_EXCEPTION(404,"文件上传异常")
    ;
    private int code;
    private String msg;
}
