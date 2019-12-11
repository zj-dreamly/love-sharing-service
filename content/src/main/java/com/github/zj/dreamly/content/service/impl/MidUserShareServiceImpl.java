package com.github.zj.dreamly.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.entity.MidUserShare;
import com.github.zj.dreamly.content.mapper.MidUserShareMapper;
import com.github.zj.dreamly.content.service.MidUserShareService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户-分享中间表【描述用户购买的分享】 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Slf4j
@Service
@AllArgsConstructor
public class MidUserShareServiceImpl extends ServiceImpl<MidUserShareMapper, MidUserShare> implements MidUserShareService {

}
