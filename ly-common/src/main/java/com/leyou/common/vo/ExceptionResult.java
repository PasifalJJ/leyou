package com.leyou.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leyou.common.enums.ExceptionsEnums;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value={"dateFormat"})
public class ExceptionResult implements Serializable {
    private Integer id;
    private String name;
    private String date;
    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
    public ExceptionResult(ExceptionsEnums exceptionsEnums){
        id=exceptionsEnums.getCode();
        name=exceptionsEnums.getMsg();
        date= dateFormat.format(new Date());
    }
}
