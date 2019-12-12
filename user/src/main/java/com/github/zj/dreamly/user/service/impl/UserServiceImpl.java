package com.github.zj.dreamly.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.user.entity.BonusEventLog;
import com.github.zj.dreamly.user.entity.User;
import com.github.zj.dreamly.user.feignclient.ContentCenterFeignClient;
import com.github.zj.dreamly.user.mapper.UserMapper;
import com.github.zj.dreamly.user.service.BonusEventLogService;
import com.github.zj.dreamly.user.service.UserService;
import com.zj.dreamly.common.dto.messaging.UserAddBonusMsgDTO;
import com.zj.dreamly.common.dto.share.ShareResponseDTO;
import com.zj.dreamly.common.dto.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 分享 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final UserMapper userMapper;

	private final BonusEventLogService bonusEventLogService;

	private final ContentCenterFeignClient contentCenterFeignClient;

	private static final int DEFAULT_BONUS = 300;

	private static final String DEFAULT_ROLE = "user";

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addBonus(UserAddBonusMsgDTO msgDTO) {
		// 1. 为用户加积分
		Integer userId = msgDTO.getUserId();
		Integer bonus = msgDTO.getBonus();
		User user = this.getById(userId);

		user.setBonus(user.getBonus() + bonus);
		this.userMapper.updateById(user);

		// 2. 记录日志到bonus_event_log表里面
		this.bonusEventLogService.save(
			BonusEventLog.builder()
				.userId(userId)
				.value(bonus)
				.event(msgDTO.getEvent())
				.createTime(new Date())
				.description(msgDTO.getDescription())
				.build()
		);
		log.info("积分添加/减少完成");
	}

	@Override
	public List<ShareResponseDTO> contributions(Integer id) {
		return contentCenterFeignClient.listByUserId(id);
	}

	@Override
	public List<BonusEventLog> bonusLogs(Integer userId) {
		return bonusEventLogService.list(Wrappers.<BonusEventLog>lambdaQuery()
			.eq(BonusEventLog::getUserId, userId));
	}

	@Override
	public User login(UserLoginDTO loginDTO, String openId) {
		User user = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getWxId, openId));
		if (user == null) {
			User userToSave = User.builder()
				.wxId(openId)
				.bonus(DEFAULT_BONUS)
				.wxNickname(loginDTO.getWxNickname())
				.avatarUrl(loginDTO.getAvatarUrl())
				.roles(DEFAULT_ROLE)
				.createTime(new Date())
				.updateTime(new Date())
				.build();
			this.save(
				userToSave
			);
			return userToSave;
		}
		return user;
	}
}
