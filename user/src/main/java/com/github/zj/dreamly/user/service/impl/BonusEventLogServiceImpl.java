package com.github.zj.dreamly.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.user.entity.BonusEventLog;
import com.github.zj.dreamly.user.mapper.BonusEventLogMapper;
import com.github.zj.dreamly.user.service.BonusEventLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 积分变更记录表 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Slf4j
@Service
@AllArgsConstructor
public class BonusEventLogServiceImpl extends ServiceImpl<BonusEventLogMapper, BonusEventLog> implements BonusEventLogService {

}
