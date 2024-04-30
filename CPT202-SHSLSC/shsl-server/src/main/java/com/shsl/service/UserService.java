package com.shsl.service;

import com.shsl.dto.*;
import com.shsl.entity.User;
import com.shsl.entity.User;
import com.shsl.result.PageBean;
import com.shsl.result.PageResult;

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

    /**
     * 分页查询
     * @param userPageQueryDTO
     * @return
     */
    PageResult selectByPage(UserPageQueryDTO userPageQueryDTO);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getById(Integer id);

    //添加数据
    void add(UserDTO userDTO);

    /**
     * 编辑用户信息
     * @param userDTO
     */
    void update(UserDTO userDTO);


    //修改数据
    void updateUserPhone(Integer id, String phone);
    void updateUserEmail(Integer id, String email);
    void updateUserSex(Integer id, String sex);
    void updateUserAvatar(Integer id, String avatar);

    /**
     * 删除员工
     * @param userDTO
     */
    void deleteUserById(UserDTO userDTO);

    //批量删除
    void deleteUserByIds(int[] ids);


}
