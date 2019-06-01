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
    CANNOT_FIND_SPECIFICATION(404,"未找到规格参数"),
    CANNOT_FIND_SPU(404,"未找到spu参数"),
    CATEGORY_HAVE_NOT_SPECIFICATION(404,"分类未找到规格参数"),
    SAVE_SPECIFICATION_ERROR(500,"保存规格失败"),
    UPDATE_SPECIFICATION_ERROR(500,"更新商品规格失败"),
    DELETE_UNKNOWN_BRAND(404,"上传id异常"),
    //private static final ExceptionsEnums e=new ExceptionsEnums (400,"价格不能为空");
    FILE_UPLOAD_EXCEPTION(500,"文件保存异常"),
    UPLOAD_FILE_EXCEPTION(404,"文件上传异常"),
    UPDATE_FILE_EXCEPTION(500,"文件更新异常"),
    SPU_PARAM_ERROR(404,"商品参数错误"),
    INSERT_SPU_ERROR(500,"保存Spu数据失败"),
    INSERT_SPUDETAIL_ERROR(500,"保存SpuDetail数据失败"),
    INSERT_SKULIST_ERROR(500,"保存sku集合失败"),
    INSERT_STOCK_ERROR(500,"保存库存错误"),

    ;
    private int code;
    private String msg;
}
