package com.liyuan.wave.po.media;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("media_file_process_m3u8")
public class MediaFileProcessM3u8 {

    @TableId(type = IdType.UUID)
    //文件id
    private String id;
    //ts列表
    private String tslist;
    private String mediaFileId;

}
