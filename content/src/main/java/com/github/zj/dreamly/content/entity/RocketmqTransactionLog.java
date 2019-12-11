package com.github.zj.dreamly.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * RocketMQ事务日志表实体类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Data
@ApiModel(value = "RocketmqTransactionLog对象", description = "RocketMQ事务日志表")
public class RocketmqTransactionLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 事务id
	 */
	@ApiModelProperty(value = "事务id")
	@TableField("transaction_Id")
	private String transactionId;
	/**
	 * 日志
	 */
	@ApiModelProperty(value = "日志")
	private String log;

}
