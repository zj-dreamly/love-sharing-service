package com.github.zj.dreamly.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分变更记录表实体类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Data
@Builder
@ApiModel(value = "BonusEventLog对象", description = "积分变更记录表")
@NoArgsConstructor
@AllArgsConstructor
public class BonusEventLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@ApiModelProperty(value = "Id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * user.id
	 */
	@ApiModelProperty(value = "user.id")
	private Integer userId;
	/**
	 * 积分操作值
	 */
	@ApiModelProperty(value = "积分操作值")
	private Integer value;
	/**
	 * 发生的事件
	 */
	@ApiModelProperty(value = "发生的事件")
	private String event;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String description;

}
