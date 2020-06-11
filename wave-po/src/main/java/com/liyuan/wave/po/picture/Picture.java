package com.liyuan.wave.po.picture;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("picture")
public class Picture implements Serializable {
	@TableId
	private String userId;
	private String pictureId;
	private String address;
}
