package com.github.zj.dreamly.user.feignclient.fallback;

import com.github.zj.dreamly.user.feignclient.ContentCenterFeignClient;
import com.zj.dreamly.common.dto.share.ShareResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>ContentCenterFeignClientFallback</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 15:01
 **/
@Slf4j
@Component
public class ContentCenterFeignClientFallback implements ContentCenterFeignClient {
	@Override
	public List<ShareResponseDTO> listByUserId(Integer id) {

		log.warn("ContentCenterFeignClient#listByUserId方法被限流/降级");
		return new ArrayList<>();
	}
}
