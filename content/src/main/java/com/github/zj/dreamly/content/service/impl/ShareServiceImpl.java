package com.github.zj.dreamly.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.content.ShareAuditDTO;
import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.entity.MidUserShare;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.feignclient.UserCenterFeignClient;
import com.github.zj.dreamly.content.mapper.ShareMapper;
import com.github.zj.dreamly.content.service.MidUserShareService;
import com.github.zj.dreamly.content.service.ShareService;
import com.github.zj.dreamly.content.util.PageInfo;
import com.github.zj.dreamly.tool.util.StreamUtil;
import com.zj.dreamly.common.dto.share.ShareRequestDTO;
import com.zj.dreamly.common.dto.user.UserAddBonusDTO;
import com.zj.dreamly.common.dto.user.UserDTO;
import com.zj.dreamly.common.enums.AuditStatusEnum;
import com.zj.dreamly.common.enums.BonusEventEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分享表 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Slf4j
@Service
@AllArgsConstructor
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

	private final MidUserShareService midUserShareService;

	private final UserCenterFeignClient userCenterFeignClient;

	@Override
	public ShareDTO getByShareId(Integer id) {

		final Share share = this.getById(id);
		ShareDTO shareDTO = new ShareDTO();

		BeanUtil.copyProperties(share, shareDTO);

		final UserDTO userDTO = userCenterFeignClient.findUserById(share.getUserId());
		shareDTO.setWxNickname(userDTO.getWxNickname());
		return shareDTO;
	}

	@Override
	public PageInfo<Share> q(String title, Integer pageNo, Integer pageSize, Integer userId) {

		final LambdaQueryWrapper<Share> wrapper = Wrappers.<Share>lambdaQuery()
			.like(Share::getTitle, title);
		wrapper.eq(Share::getAuditStatus, AuditStatusEnum.PASS.name());

		final IPage<Share> page = this.page(new Page<>(pageNo, pageSize), wrapper);

		final List<Share> shares = page.getRecords();
		List<Share> sharesDeal;

		sharesDeal = shares.stream()
			.peek(share -> {
				MidUserShare midUserShare = this.midUserShareService.getOne(
					Wrappers.query(
						MidUserShare.builder()
							.userId(userId)
							.shareId(share.getId())
							.build())
				);
				if (midUserShare == null) {
					share.setDownloadUrl(null);
				}
			})
			.collect(Collectors.toList());

		return new PageInfo<>(sharesDeal);
	}

	@Override
	public Share exchangeById(Integer id, HttpServletRequest request) {
		Object userId = request.getAttribute("id");
		Integer integerUserId = (Integer) userId;

		// 1. 根据id查询share，校验是否存在
		Share share = this.getById(id);
		if (share == null) {
			throw new IllegalArgumentException("该分享不存在！");
		}
		Integer price = share.getPrice();

		// 2. 如果当前用户已经兑换过该分享，则直接返回
		MidUserShare midUserShare = this.midUserShareService.getOne(
			Wrappers.query(
				MidUserShare.builder()
					.shareId(id)
					.userId(integerUserId)
					.build())
		);
		if (midUserShare != null) {
			return share;
		}

		// 3. 根据当前登录的用户id，查询积分是否够
		UserDTO userDTO = this.userCenterFeignClient.findUserById(integerUserId);
		if (price > userDTO.getBonus()) {
			throw new IllegalArgumentException("用户积分不够用！");
		}

		// 4. 扣减积分 & 往mid_user_share里插入一条数据
		this.userCenterFeignClient.addBonus(
			UserAddBonusDTO.builder()
				.userId(integerUserId)
				.bonus(0 - price)
				.build()
		);
		this.midUserShareService.save(
			MidUserShare.builder()
				.userId(integerUserId)
				.shareId(id)
				.build()
		);

		//增加购买次数
		share.setBuyCount(share.getBuyCount() + 1);
		this.updateById(share);
		return share;
	}

	@Override
	public Share auditById(Integer id, ShareAuditDTO auditDTO, Integer userId) {
		// 1. 查询share是否存在，不存在或者当前的audit_status != NOT_YET，那么抛异常
		Share share = this.getById(id);
		if (share == null) {
			throw new IllegalArgumentException("参数非法！该分享不存在！");
		}
		if (!AuditStatusEnum.NOT_YET.name().equals(share.getAuditStatus())) {
			throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
		}

		share.setAuditStatus(AuditStatusEnum.PASS.name());
		share.setReason(auditDTO.getReason());
		this.updateById(share);

		// 3. 如果是PASS，让用户中心去消费，并为发布人添加积分（暂时通过调用用户服务，后期通过实现MQ异步发送）
		userCenterFeignClient.addBonus(new UserAddBonusDTO(userId, 50, BonusEventEnum.RECORDS.value,
			BonusEventEnum.RECORDS.desc));

		return share;
	}

	@Override
	public Share contribute(Integer userId, ShareRequestDTO shareRequestDTO) {

		if (null == userId) {
			throw new RuntimeException("用户登录状态异常");
		}

		if (StrUtil.hasBlank(shareRequestDTO.getAuthor(),
			shareRequestDTO.getDownloadUrl(), shareRequestDTO.getPrice(),
			shareRequestDTO.getSummary(), shareRequestDTO.getTitle())) {

			throw new RuntimeException("请完善投稿内容");

		}

		final Date date = new Date();
		final Share share = Share.builder()
			.userId(userId)
			.title(shareRequestDTO.getTitle())
			.createTime(date)
			.updateTime(date)
			.auditStatus(AuditStatusEnum.NOT_YET.name())
			.isOriginal(shareRequestDTO.isOriginal())
			.author(shareRequestDTO.getAuthor())
			.downloadUrl(shareRequestDTO.getDownloadUrl())
			.price(Integer.valueOf(shareRequestDTO.getPrice()))
			.summary(shareRequestDTO.getSummary())
			.build();
		this.save(share);
		return share;
	}

	@Override
	public List<Share> listByUserId(Integer id) {
		return this.list(Wrappers.<Share>lambdaQuery().eq(Share::getUserId, id));
	}

	@Override
	public Share updateContribute(Integer id, ShareRequestDTO shareRequestDTO) {

		if (StrUtil.hasBlank(shareRequestDTO.getAuthor(),
			shareRequestDTO.getDownloadUrl(), shareRequestDTO.getPrice(),
			shareRequestDTO.getSummary(), shareRequestDTO.getTitle())) {

			throw new RuntimeException("请完善投稿内容");

		}
		final Share share = this.getById(id);
		if (null == share) {
			throw new RuntimeException("此分享信息不存在");
		}

		share.setAuthor(shareRequestDTO.getAuthor());
		share.setDownloadUrl(shareRequestDTO.getDownloadUrl());
		share.setIsOriginal(shareRequestDTO.isOriginal());
		share.setPrice(Integer.valueOf(shareRequestDTO.getPrice()));
		share.setSummary(shareRequestDTO.getSummary());
		share.setTitle(shareRequestDTO.getTitle());

		this.updateById(share);
		return share;
	}

	@Override
	public Collection<Share> user(Integer userId) {

		final List<MidUserShare> list = midUserShareService
			.list(Wrappers.<MidUserShare>lambdaQuery().eq(MidUserShare::getUserId, userId));

		List<Integer> shareIds = StreamUtil.map(list, MidUserShare::getShareId);
		return this.listByIds(shareIds);
	}
}
