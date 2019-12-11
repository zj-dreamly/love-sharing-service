package com.github.zj.dreamly.content.controller;

import com.github.zj.dreamly.content.entity.Notice;
import com.github.zj.dreamly.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h2>Newest</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 16:01
 **/
@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/newest")
	public Notice newest(){
		return noticeService.list().get(0);
	}
}
