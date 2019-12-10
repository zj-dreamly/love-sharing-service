package com.github.zj.dreamly.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享实体类
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
@Data
@ApiModel(value = "User对象", description = "分享")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@ApiModelProperty(value = "Id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 微信id
	 */
	@ApiModelProperty(value = "微信id")
	private String wxId;
	/**
	 * 微信昵称
	 */
	@ApiModelProperty(value = "微信昵称")
	private String wxNickname;
	/**
	 * 角色
	 */
	@ApiModelProperty(value = "角色")
	private String roles;
	/**
	 * 头像地址
	 */
	@ApiModelProperty(value = "头像地址")
	private String avatarUrl;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**
	 * 积分
	 */
	@ApiModelProperty(value = "积分")
	private Integer bonus;

}
