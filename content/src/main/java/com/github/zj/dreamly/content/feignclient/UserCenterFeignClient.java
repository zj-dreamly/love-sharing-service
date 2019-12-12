package com.github.zj.dreamly.content.feignclient;

import com.github.zj.dreamly.content.feignclient.fallback.UserCenterFeignClientFallback;
import com.zj.dreamly.common.constant.FeignClientConstant;
import com.zj.dreamly.common.dto.user.UserAddBonseDTO;
import com.zj.dreamly.common.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <h2>UserCenterFeignClient</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 11:24
 **/
@FeignClient(name = FeignClientConstant.USER_CENTER,
	fallback = UserCenterFeignClientFallback.class)
public interface UserCenterFeignClient {

	String BASE_URI = "/users";

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param id userId
	 * @return {@link UserDTO}
	 */
	@GetMapping(BASE_URI + "/{id}")
	UserDTO findUserById(@PathVariable("id") Integer id);

	/**
	 * 为用户增加积分
	 *
	 * @param userAddBonseDTO {@link UserAddBonseDTO}
	 */
	@PutMapping(BASE_URI + "/add-bonus")
	void addBonus(@RequestBody UserAddBonseDTO userAddBonseDTO);
}
