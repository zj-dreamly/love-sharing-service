package com.github.zj.dreamly.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zj.dreamly.content.content.ShareAuditDTO;
import com.github.zj.dreamly.content.content.ShareDTO;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.util.PageInfo;
import com.zj.dreamly.common.dto.share.ShareRequestDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 分享表 服务类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
public interface ShareService extends IService<Share> {

	/**
	 * 根据id获取分享详情
	 *
	 * @param id id
	 * @return {@link ShareDTO}
	 */
	ShareDTO getByShareId(Integer id);

	/**
	 * 分页列表
	 *
	 * @param title    标题
	 * @param pageNo   页数
	 * @param pageSize 页面大小
	 * @param userId   用户id
	 * @return {@link PageInfo}
	 */
	PageInfo<Share> q(String title, Integer pageNo, Integer pageSize, Integer userId);

	/**
	 * 分享兑换
	 *
	 * @param id      用户id
	 * @param request request
	 * @return {@link Share}
	 */
	Share exchangeById(Integer id, HttpServletRequest request);

	/**
	 * 审核
	 *
	 * @param id       分享内容id
	 * @param auditDTO {@link ShareAuditDTO}
	 * @param userId   用户Id
	 * @return {@link Share}
	 */
	Share auditById(Integer id, ShareAuditDTO auditDTO, Integer userId);

	/**
	 * 投稿接口
	 *
	 * @param userId          当前用户id
	 * @param shareRequestDTO {@link ShareRequestDTO}
	 * @return {@link Share}
	 */
	Share contribute(Integer userId, ShareRequestDTO shareRequestDTO);

	/**
	 * 根据分享人的id查看此用户的投稿
	 *
	 * @param id 投稿人id
	 * @return {@link Share}
	 */
	List<Share> listByUserId(Integer id);

	/**
	 * 修改投稿
	 *
	 * @param id              投稿id
	 * @param shareRequestDTO {@link ShareRequestDTO}
	 * @return {@link Share}
	 */
	Share updateContribute(Integer id, ShareRequestDTO shareRequestDTO);

	/**
	 * 我的兑换
	 *
	 * @param userId 用户id
	 * @return {@link Share}
	 */
	Collection<Share> user(Integer userId);
}
