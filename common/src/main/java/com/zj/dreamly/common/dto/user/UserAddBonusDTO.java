package com.zj.dreamly.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍海之南
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddBonusDTO {
	private Integer userId;
	/**
	 * 积分
	 */
	private Integer bonus;

	/**
	 * 事件类型
	 */
	private String event;
	/**
	 * 描述
	 */
	private String desc;
}
