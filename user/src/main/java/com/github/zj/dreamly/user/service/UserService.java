package com.github.zj.dreamly.user.service;

import com.github.zj.dreamly.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zj.dreamly.tool.util.PageQuery;

/**
 * 分享 服务类
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
public interface UserService extends IService<User> {

    /**
     * 根据id查询分享
     *
     * @param id 主键id
     * @return {@link User}
     */
    User getUserById(Long id);

    /**
     * 分页查询分享
     *
     * @param query {@link PageQuery}
     * @return {@link User}
     */
    IPage<User> getUserPage(PageQuery query);

    /**
     * 新增分享
     *
     * @param user {@link User}
     */
    void saveUser(User user);

    /**
     * 修改分享
     *
     * @param user {@link User}
     */
    void updateUserById(User user);

    /**
     * 删除分享
     *
     * @param id 主键id
     */
    void removeUserById(Long id);
}
