package com.github.zj.dreamly.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.content.ShareAuditDTO;
import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.mapper.ShareMapper;
import com.github.zj.dreamly.content.service.ShareService;
import com.github.zj.dreamly.content.util.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public ShareDTO getByShareId(Integer id) {
		return null;
	}

	@Override
	public PageInfo<Share> q(String title, Integer pageNo, Integer pageSize, Integer userId) {
		return null;
	}

	@Override
	public Share exchangeById(Integer id, HttpServletRequest request) {
		return null;
	}

	@Override
	public Share auditById(Integer id, ShareAuditDTO auditDTO) {

		return null;
	}
}
