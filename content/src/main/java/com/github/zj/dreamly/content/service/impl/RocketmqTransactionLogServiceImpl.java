package com.github.zj.dreamly.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zj.dreamly.content.entity.RocketmqTransactionLog;
import com.github.zj.dreamly.content.mapper.RocketmqTransactionLogMapper;
import com.github.zj.dreamly.content.service.RocketmqTransactionLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RocketMQ事务日志表 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-11
 */
@Slf4j
@Service
@AllArgsConstructor
public class RocketmqTransactionLogServiceImpl extends ServiceImpl<RocketmqTransactionLogMapper, RocketmqTransactionLog> implements RocketmqTransactionLogService {

}
