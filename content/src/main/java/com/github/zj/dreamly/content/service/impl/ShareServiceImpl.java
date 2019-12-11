package com.github.zj.dreamly.content.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.content.ShareAuditDTO;
import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.dto.share.ShareRequestDTO;
import com.github.zj.dreamly.content.entity.MidUserShare;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.mapper.ShareMapper;
import com.github.zj.dreamly.content.service.MidUserShareService;
import com.github.zj.dreamly.content.service.ShareService;
import com.github.zj.dreamly.content.util.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

	@Override
	public ShareDTO getByShareId(Integer id) {

		final Share share = this.getById(id);
		ShareDTO shareDTO = new ShareDTO();

		BeanUtil.copyProperties(share, shareDTO);
		return shareDTO;
	}

	@Override
	public PageInfo<Share> q(String title, Integer pageNo, Integer pageSize, Integer userId) {

		final IPage<Share> page = this.page(new Page<>(pageNo, pageSize),
			Wrappers.<Share>lambdaQuery().like(Share::getTitle, title));

		final List<Share> shares = page.getRecords();
		List<Share> sharesDeal;
		// 1. 如果用户未登录，那么downloadUrl全部设为null
		if (userId == null) {
			sharesDeal = shares.stream()
				.peek(share -> {
					share.setDownloadUrl(null);
				})
				.collect(Collectors.toList());
		}
		// 2. 如果用户登录了，那么查询一下mid_user_share，如果没有数据，那么这条share的downloadUrl也设为null
		else {
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
		}

		return new PageInfo<>(sharesDeal);
	}

	@Override
	public Share exchangeById(Integer id, HttpServletRequest request) {
		return null;
	}

	@Override
	public Share auditById(Integer id, ShareAuditDTO auditDTO) {

		return null;
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
			.isOriginal(shareRequestDTO.isOriginal())
			.author(shareRequestDTO.getAuthor())
			.downloadUrl(shareRequestDTO.getDownloadUrl())
			.price(Integer.valueOf(shareRequestDTO.getPrice()))
			.summary(shareRequestDTO.getSummary())
			.build();
		this.save(share);
		return share;
	}
}
