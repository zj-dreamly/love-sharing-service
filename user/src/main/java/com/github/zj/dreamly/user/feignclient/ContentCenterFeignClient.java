package com.github.zj.dreamly.user.feignclient;

import com.github.zj.dreamly.user.feignclient.fallback.ContentCenterFeignClientFallback;
import com.zj.dreamly.common.constant.FeignClientConstant;
import com.zj.dreamly.common.dto.share.ShareResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <h2>ContentCenterFeignClient</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 14:25
 **/
@FeignClient(name = FeignClientConstant.CONTENT_CENTER, fallback = ContentCenterFeignClientFallback.class)
public interface ContentCenterFeignClient {

	String BASE_URI = "shares";

	/**
	 * 根据用户id查询投稿
	 *
	 * @param id 用户id
	 * @return {@link ShareResponseDTO}
	 */
	@GetMapping(BASE_URI + "/contributions/{id}")
	List<ShareResponseDTO> listByUserId(@PathVariable("id") Integer id);
}
