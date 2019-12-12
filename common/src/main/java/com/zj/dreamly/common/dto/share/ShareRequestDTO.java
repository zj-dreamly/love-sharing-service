package com.zj.dreamly.common.dto.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h2>shareRequestDTO</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 14:24
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareRequestDTO {

	/**
	 * 作者
	 */
	private String author;
	/**
	 * 下载地址
	 */
	private String downloadUrl;
	/**
	 * 是否原创
	 */
	private boolean isOriginal;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 简介
	 */
	private String summary;
	/**
	 * 标题
	 */
	private String title;

}
