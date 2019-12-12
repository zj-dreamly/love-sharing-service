package com.github.zj.dreamly.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zj.dreamly.user.entity.BonusEventLog;
import com.github.zj.dreamly.user.entity.User;
import com.zj.dreamly.common.dto.messaging.UserAddBonusMsgDTO;
import com.zj.dreamly.common.dto.share.ShareResponseDTO;
import com.zj.dreamly.common.dto.user.UserLoginDTO;

import java.util.List;

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
	 * 给用户添加积分
	 *
	 * @param msgDTO {@link UserAddBonusMsgDTO}
	 */
	void addBonus(UserAddBonusMsgDTO msgDTO);

	/**
	 * 根据用户id获取我的投稿
	 * @param id id
	 * @return {@link ShareResponseDTO}
	 */
	List<ShareResponseDTO> contributions(Integer id);

	/**
	 * 获取用户积分变化情况
	 * @param userId 用户id
	 * @return {@link BonusEventLog}
	 */
	List<BonusEventLog> bonusLogs(Integer userId);
}
