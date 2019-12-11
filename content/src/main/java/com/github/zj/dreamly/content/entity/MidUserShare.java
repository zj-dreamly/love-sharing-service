package com.github.zj.dreamly.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-分享中间表【描述用户购买的分享】实体类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Data
@ApiModel(value = "MidUserShare对象", description = "用户-分享中间表【描述用户购买的分享】")
public class MidUserShare implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * share.id
	 */
	@ApiModelProperty(value = "share.id")
	private Integer shareId;
	/**
	 * user.id
	 */
	@ApiModelProperty(value = "user.id")
	private Integer userId;

}
