package com.github.zj.dreamly.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享表实体类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Data
@ApiModel(value = "Share对象", description = "分享表")
public class Share implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 发布人id
	 */
	@ApiModelProperty(value = "发布人id")
	private Integer userId;
	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String title;
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
	 * 是否原创 0:否 1:是
	 */
	@ApiModelProperty(value = "是否原创 0:否 1:是")
	private Boolean isOriginal;
	/**
	 * 作者
	 */
	@ApiModelProperty(value = "作者")
	private String author;
	/**
	 * 封面
	 */
	@ApiModelProperty(value = "封面")
	private String cover;
	/**
	 * 概要信息
	 */
	@ApiModelProperty(value = "概要信息")
	private String summary;
	/**
	 * 价格（需要的积分）
	 */
	@ApiModelProperty(value = "价格（需要的积分）")
	private Integer price;
	/**
	 * 下载地址
	 */
	@ApiModelProperty(value = "下载地址")
	private String downloadUrl;
	/**
	 * 下载数
	 */
	@ApiModelProperty(value = "下载数 ")
	private Integer buyCount;
	/**
	 * 是否显示 0:否 1:是
	 */
	@ApiModelProperty(value = "是否显示 0:否 1:是")
	private Boolean showFlag;
	/**
	 * 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
	 */
	@ApiModelProperty(value = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过")
	private String auditStatus;
	/**
	 * 审核不通过原因
	 */
	@ApiModelProperty(value = "审核不通过原因")
	private String reason;

}
