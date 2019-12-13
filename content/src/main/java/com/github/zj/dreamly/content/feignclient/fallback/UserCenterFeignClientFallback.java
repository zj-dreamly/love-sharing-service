package com.github.zj.dreamly.content.feignclient.fallback;

import com.github.zj.dreamly.content.feignclient.UserCenterFeignClient;
import com.zj.dreamly.common.dto.user.UserAddBonusDTO;
import com.zj.dreamly.common.dto.user.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h2>UserCenterFeignClientFallback</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 11:44
 **/
@Slf4j
@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {
	@Override
	public UserDTO findUserById(Integer id) {
		log.warn("[UserCenterFeignClient#findUserById]方法调用已被限流/降级.");
		return new UserDTO();
	}

	@Override
	public void addBonus(UserAddBonusDTO userAddBonusDTO) {
		log.warn("[UserCenterFeignClient#addBonus]方法调用已被限流/降级.");
	}
}
