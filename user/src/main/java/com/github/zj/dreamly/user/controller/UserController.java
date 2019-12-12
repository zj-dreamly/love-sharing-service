package com.github.zj.dreamly.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.bean.BeanUtil;
import com.github.zj.dreamly.user.entity.User;
import com.github.zj.dreamly.user.service.UserService;
import com.zj.dreamly.common.auth.CheckLogin;
import com.zj.dreamly.common.dto.messaging.UserAddBonusMsgDTO;
import com.zj.dreamly.common.dto.share.ShareResponseDTO;
import com.zj.dreamly.common.dto.user.*;
import com.zj.dreamly.common.util.JwtOperator;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分享 控制器
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Api(value = "分享", tags = "分享接口")
@Validated
public class UserController {

	private final UserService userService;
	private final WxMaService wxMaService;
	private final JwtOperator jwtOperator;

	@PostMapping("/login")
	public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO) throws WxErrorException {
		// 微信小程序服务端校验是否已经登录的结果
		WxMaJscode2SessionResult result = this.wxMaService.getUserService()
			.getSessionInfo(loginDTO.getCode());

		// 微信的openId，用户在微信这边的唯一标示
		String openid = result.getOpenid();

		// 看用户是否注册，如果没有注册就（插入）
		// 如果已经注册
		User user = this.userService.login(loginDTO, openid);

		// 颁发token
		Map<String, Object> userInfo = new HashMap<>(3);
		userInfo.put("id", user.getId());
		userInfo.put("wxNickname", user.getWxNickname());
		userInfo.put("role", user.getRoles());

		String token = jwtOperator.generateToken(userInfo);

		log.info(
			"用户{}登录成功，生成的token = {}, 有效期到:{}",
			loginDTO.getWxNickname(),
			token,
			jwtOperator.getExpirationTime()
		);

		// 构建响应
		return LoginRespDTO.builder()
			.user(
				UserRespDTO.builder()
					.id(user.getId())
					.avatarUrl(user.getAvatarUrl())
					.bonus(user.getBonus())
					.wxNickname(user.getWxNickname())
					.build()
			)
			.token(
				JwtTokenRespDTO.builder()
					.expirationTime(jwtOperator.getExpirationTime().getTime())
					.token(token)
					.build()
			)
			.build();
	}

	@PutMapping("/add-bonus")
	public User addBonus(@RequestBody UserAddBonseDTO userAddBonseDTO) {
		Integer userId = userAddBonseDTO.getUserId();
		userService.addBonus(
			UserAddBonusMsgDTO.builder()
				.userId(userId)
				.bonus(userAddBonseDTO.getBonus())
				.description("兑换分享")
				.event("BUY")
				.build()
		);
		return this.userService.getById(userId);
	}

	@GetMapping("/me")
	@CheckLogin
	public User me(HttpServletRequest request) {
		final Integer id = Integer.valueOf(request.getAttribute("id").toString());
		return userService.getById(id);
	}

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param id userId
	 * @return {@link UserDTO}
	 */
	@GetMapping("/{id}")
	public UserDTO findUserById(@PathVariable("id") Integer id) {

		final User user = userService.getById(id);

		UserDTO userDTO = new UserDTO();
		BeanUtil.copyProperties(user, userDTO);
		return userDTO;
	}

	/**
	 * 我的投稿
	 */
	@GetMapping("/contributions")
	@CheckLogin
	public List<ShareResponseDTO> contributions(HttpServletRequest request) {
		final Integer id = Integer.valueOf(request.getAttribute("id").toString());
		return userService.contributions(id);
	}

}
