package com.shsl.service;

import com.shsl.dto.*;
import com.shsl.entity.User;
import com.shsl.entity.User;
import com.shsl.result.PageBean;
import com.shsl.result.PageResult;

import java.util.List;

public interface UserService {
    /**
     * User log-in
     * @param userLoginDTO
     * @return Admin
     */
    User login(UserLoginDTO userLoginDTO);

    /**
     * WeChat login
     * @param weChatLoginDTO
     * @return Use
     */
    User wxLogin(WeChatLoginDTO weChatLoginDTO);

    /**
     * Query all records
     * @return List<User>
     */
    List<User> selectAll();

    /**
     * Paging query
     * @param userPageQueryDTO
     * @return PageResult
     */
    PageResult selectByPage(UserPageQueryDTO userPageQueryDTO);

    /**
     * Query user by id
     * @param id
     * @return User
     */
    User getById(Integer id);

    /**
     * Add new user
     * @param userRegisterDTO
     */
    void insert(UserRegisterDTO userRegisterDTO);

    /**
     * Edit user information
     * @param userDTO
     */
    void update(UserDTO userDTO);

    /**
     * Update user data
     * @param id
     * @param phone, email, sex, avatar
     */
    void updateUserPhone(Integer id, String phone);
    void updateUserEmail(Integer id, String email);
    void updateUserSex(Integer id, String sex);
    void updateUserAvatar(Integer id, String avatar);

    /**
     * Delete user by id
     * @param userDTO
     */
    void deleteUserById(UserDTO userDTO);

    /**
     * Delete in batches
     * @param ids
     */
    void deleteUserByIds(int[] ids);


}
