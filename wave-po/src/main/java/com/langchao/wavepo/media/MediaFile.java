package com.langchao.wavepo.media;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("media_file")
public class MediaFile {
	@TableId
	//文件id
	private String fileId;
	//文件名称
	private String fileName;
	//文件原始名称
	private String fileOriginalName;
	//文件路径
	private String filePath;
	//文件url
	private String fileUrl;
	//文件类型
	private String fileType;
	//文件大小
	private Long fileSize;
	//文件状态
	private String fileStatus;
	//上传时间
	private Date uploadTime;

}
