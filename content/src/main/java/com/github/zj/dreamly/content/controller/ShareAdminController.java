package com.github.zj.dreamly.content.controller;

import com.github.zj.dreamly.content.content.ShareAuditDTO;
import com.github.zj.dreamly.content.entity.Share;
import com.github.zj.dreamly.content.service.ShareService;
import com.zj.dreamly.common.auth.CheckAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <h2>ShareAdminController</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 11:41
 **/
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {

	private final ShareService shareService;
	@PutMapping("/audit/{id}")
	@CheckAuthorization("admin")
	public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO auditDTO) {
		return this.shareService.auditById(id, auditDTO);
	}

}
