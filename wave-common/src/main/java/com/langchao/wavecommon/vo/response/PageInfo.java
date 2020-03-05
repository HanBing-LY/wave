package com.langchao.wavecommon.vo.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class PageInfo<T> {

    private List<T> list;

    private long total;
}
