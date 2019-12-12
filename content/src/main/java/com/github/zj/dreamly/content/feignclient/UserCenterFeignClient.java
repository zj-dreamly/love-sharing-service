package com.github.zj.dreamly.content.feignclient;

import com.github.zj.dreamly.content.feignclient.fallback.UserCenterFeignClientFallback;
import com.zj.dreamly.common.constant.FeignClientConstant;
import com.zj.dreamly.common.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <h2>UserCenterFeignClient</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 11:24
 **/
@FeignClient(name = FeignClientConstant.USER_CENTER,
	fallback = UserCenterFeignClientFallback.class)
public interface UserCenterFeignClient {

	/**
	 * 根据用户名获取用户信息
	 * @param id userId
	 * @return {@link UserDTO}
	 */
	@GetMapping("/users/{id}")
	UserDTO findUserById(@PathVariable("id") Integer id);
}
