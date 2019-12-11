package com.github.zj.dreamly.content.controller;

import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.service.ShareService;
import com.github.zj.dreamly.content.util.PageInfo;
import com.zj.dreamly.common.auth.CheckLogin;
import com.zj.dreamly.common.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 分享表 控制器
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("shares")
@Api(value = "分享表", tags = "分享表接口")
@Validated
public class ShareController {

	private final ShareService shareService;
	private final JwtOperator jwtOperator;

	@GetMapping("/{id}")
	@CheckLogin
	public ShareDTO findById(@PathVariable Integer id) {
		return this.shareService.getByShareId(id);
	}

	@GetMapping("/q")
	public PageInfo<Share> q(
		@RequestParam(required = false) String title,
		@RequestParam(required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(required = false, defaultValue = "10") Integer pageSize,
		@RequestHeader(value = "X-Token", required = false) String token) {

		Integer userId = null;
		if (StringUtils.isNotBlank(token)) {
			Claims claims = this.jwtOperator.getClaimsFromToken(token);
			userId = (Integer) claims.get("id");
		}
		return this.shareService.q(title, pageNo, pageSize, userId);
	}

	@GetMapping("/exchange/{id}")
	@CheckLogin
	public Share exchangeById(@PathVariable Integer id, HttpServletRequest request) {
		return this.shareService.exchangeById(id, request);
	}

}