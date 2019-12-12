package com.zj.dreamly.common.enums;

import lombok.Getter;

/**
 * <h2>BonusEventEnum</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 16:24
 **/
@Getter
public enum BonusEventEnum {

	/**
	 * buy
	 */
	BUY("BUY", "兑换"),

	SIGN("sign", "签到"),

	RECORDS("records", "投稿"),

	SHARE("share", "分享");

	public String value;

	public String desc;

	BonusEventEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
}
