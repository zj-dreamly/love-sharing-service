package com.github.zj.dreamly.content.content;

import lombok.Data;

/**
 * @author 苍海之南
 */
@Data
public class ShareAuditDTO {
	/**
	 * 审核状态
	 */
	private String auditStatusEnum;
	/**
	 * 原因
	 */
	private String reason;
}
