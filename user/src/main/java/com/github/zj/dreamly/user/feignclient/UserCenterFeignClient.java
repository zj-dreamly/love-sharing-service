package com.github.zj.dreamly.user.feignclient;

import com.zj.dreamly.common.constant.FeignClientConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <h2>UserCenterFeignClient</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-12 11:24
 **/
@FeignClient(name = FeignClientConstant.USER_CENTER)
public interface UserCenterFeignClient {

}
