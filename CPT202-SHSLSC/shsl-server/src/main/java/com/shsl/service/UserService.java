package com.shsl.service;

import com.shsl.dto.UserLoginDTO;
import com.shsl.dto.WeChatLoginDTO;
import com.shsl.entity.User;
import com.shsl.result.PageBean;

import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * 微信登录
     * @param weChatLoginDTO
     * @return
     */
    User wxLogin(WeChatLoginDTO weChatLoginDTO);

    //查询所有
    List<User> selectAll();

    //添加数据
    void add(User user);

    //修改数据
    void updateUserEmail(Integer id, String email);
    void updateUserSex(Integer id, String sex);
    void updateUserAvatar(Integer id, String avatar);

    //删除数据
    void deleteUserById(Integer id);

    //分页查询
    PageBean<User> selectByPage(int currentPage, int pageSize);

    //批量删除
    void deleteByIds(int[] ids);

    //条件查询
    PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user);
}
