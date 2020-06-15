package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description oms_shopping_cart_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_shopping_cart_info")
public class OmsShoppingCartInfoEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 购物车编码
	 */
	private String shoppingCartNumber;
	/**
	 * 用户
	 */
	private Long userId;
	/**
	 * 总数量
	 */
	private Long totalCount;
	/**
	 * 总价格
	 */
	private BigDecimal totalPrice;


}
