package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionsEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LyException extends RuntimeException {
    private ExceptionsEnums exceptionsEnums;

}
