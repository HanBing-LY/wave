package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 会员与产品分类关系表（用户喜欢的分类）
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_member_product_category_relation")
public class UmsMemberProductCategoryRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long memberId;
	/**
	 * 
	 */
	private Long productCategoryId;

}
