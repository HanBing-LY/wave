package com.langchao.wavecommon.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionToExtends extends RuntimeException {
    private ExceptionResult exceptionResult;
}
