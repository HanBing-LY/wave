package com.liyuan.wave.po.media.request;


import lombok.Data;

@Data
public class QueryMediaFileRequest  {

    private String fileOriginalName;
    private String processStatus;
    private String tag;
}
