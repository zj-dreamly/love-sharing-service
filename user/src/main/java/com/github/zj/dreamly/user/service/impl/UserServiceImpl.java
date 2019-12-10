package com.github.zj.dreamly.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zj.dreamly.user.entity.User;
import com.github.zj.dreamly.user.mapper.UserMapper;
import com.github.zj.dreamly.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zj.dreamly.tool.util.PageQuery;

/**
 * 分享 服务实现类
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据id查询分享
     *
     * @param id 主键id
     * @return {@link User}
     */
    @Override
    public User getUserById(Long id){
        return this.getById(id);
    }

    /**
     * 分页查询分享
     *
     * @param query {@link PageQuery}
     * @return {@link User}
     */
    @Override
    public IPage<User> getUserPage(PageQuery query){
        return this.page(new Page<>(query.getCurrent(), query.getSize()));
    }

    /**
     * 新增分享
     *
     * @param user {@link User}
     */
    @Override
    public void saveUser(User user){
        this.save(user);
    }

    /**
     * 修改分享
     *
     * @param user {@link User}
     */
    @Override
    public void updateUserById(User user){
        this.updateById(user);
    }

    /**
     * 删除分享
     *
     * @param id 主键id
     */
    @Override
    public void removeUserById(Long id){
        this.removeById(id);
    }
}
