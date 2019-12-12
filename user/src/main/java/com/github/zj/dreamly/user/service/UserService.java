package com.github.zj.dreamly.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.dreamly.common.dto.messaging.UserAddBonusMsgDTO;
import com.zj.dreamly.common.dto.user.UserLoginDTO;
import com.github.zj.dreamly.user.entity.User;

/**
 * 分享 服务类
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
public interface UserService extends IService<User> {

	/**
	 * 小程序登录方法
	 *
	 * @param loginDTO {@link com.zj.dreamly.common.dto.user.LoginRespDTO}
	 * @param openid   微信在小程序的唯一标识
	 * @return 登录用户
	 */
	User login(UserLoginDTO loginDTO, String openid);

	/**
	 *  给用户添加几分
	 * @param msgDTO {@link UserAddBonusMsgDTO}
	 */
	public void addBonus(UserAddBonusMsgDTO msgDTO);
}
