package com.github.zj.dreamly.content.controller;

import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.service.ShareService;
import com.github.zj.dreamly.content.util.PageInfo;
import com.zj.dreamly.common.auth.CheckLogin;
import com.zj.dreamly.common.constant.SystemConstant;
import com.zj.dreamly.common.dto.share.ShareRequestDTO;
import com.zj.dreamly.common.util.JwtOperator;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 分享表 控制器
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shares")
@Api(value = "分享表", tags = "分享表接口")
@Validated
public class ShareController {

	private final ShareService shareService;
	private final JwtOperator jwtOperator;

	@GetMapping("/preview/{id}")
	@CheckLogin
	public ShareDTO findById(@PathVariable Integer id) {
		return this.shareService.getByShareId(id);
	}

	@GetMapping("/{id}")
	@CheckLogin
	public ShareDTO detail(@PathVariable Integer id) {
		return this.shareService.getByShareId(id);
	}

	@GetMapping("/contributions/{id}")
	public List<Share> listByUserId(@PathVariable Integer id) {
		return this.shareService.listByUserId(id);
	}

	@GetMapping("/q")
	@CheckLogin
	public PageInfo<Share> q(
		@RequestParam(required = false) String type,
		@RequestParam(required = false) String title,
		@RequestParam(required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(required = false, defaultValue = "10") Integer pageSize,
		@RequestHeader(value = SystemConstant.TOKEN_HEADER, required = false) String token) {

		Integer userId = jwtOperator.getUserId(token);
		return this.shareService.q(type, title, pageNo, pageSize, userId);
	}

	@GetMapping("/exchange/{id}")
	@CheckLogin
	public Share exchangeById(@PathVariable Integer id, HttpServletRequest request) {
		return this.shareService.exchangeById(id, request);
	}

	@PostMapping("/contribute")
	@CheckLogin
	public Share contribute(@RequestHeader(value = SystemConstant.TOKEN_HEADER, required = false) String token,
							@RequestBody ShareRequestDTO shareRequestDTO) {

		Integer userId = jwtOperator.getUserId(token);
		return this.shareService.contribute(userId, shareRequestDTO);
	}

	@PutMapping("/contribute/{id}")
	@CheckLogin
	public Share updateContribute(@PathVariable("id") Integer id, @RequestBody ShareRequestDTO shareRequestDTO) {

		return this.shareService.updateContribute(id, shareRequestDTO);
	}

	/**
	 * 我的兑换
	 */
	@GetMapping("/user")
	@CheckLogin
	public Collection<Share> user(@RequestHeader(value = SystemConstant.TOKEN_HEADER, required = false) String token) {
		Integer userId = jwtOperator.getUserId(token);
		return this.shareService.user(userId);
	}

}
