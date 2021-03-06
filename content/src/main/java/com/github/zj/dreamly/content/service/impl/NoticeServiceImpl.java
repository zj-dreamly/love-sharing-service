package com.github.zj.dreamly.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.entity.Notice;
import com.github.zj.dreamly.content.mapper.NoticeMapper;
import com.github.zj.dreamly.content.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Slf4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
