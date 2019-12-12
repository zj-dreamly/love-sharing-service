package com.zj.dreamly.common.dto.messaging;

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
public class UserAddBonusMsgDTO {
	/**
	 * 为谁加积分
	 */
	private Integer userId;
	/**
	 * 加多少积分
	 */
	private Integer bonus;

	private String description;

	private String event;
}
