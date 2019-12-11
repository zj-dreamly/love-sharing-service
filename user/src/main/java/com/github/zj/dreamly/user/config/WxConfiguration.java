package com.github.zj.dreamly.user.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 苍海之南
 */
@Configuration
@RefreshScope
public class WxConfiguration {

	@Value("${mini.appKey}")
	private String appKey;

	@Value("${mini.appSecret}")
	private String appSecret;

	@Bean
	public WxMaConfig wxMaConfig() {
		WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
		config.setAppid(appKey);
		config.setSecret(appSecret);
		return config;
	}

	@Bean
	public WxMaService wxMaService(WxMaConfig wxMaConfig) {
		WxMaServiceImpl service = new WxMaServiceImpl();
		service.setWxMaConfig(wxMaConfig);
		return service;
	}
}
