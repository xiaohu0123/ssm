package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据id删除用户
     */
    void deleteUserById(Integer id);

    /**
     * 根据参数修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据输入的参数进行模糊查询
     * @param
     * @return
     */
    List<User> findByName(String name);
}
