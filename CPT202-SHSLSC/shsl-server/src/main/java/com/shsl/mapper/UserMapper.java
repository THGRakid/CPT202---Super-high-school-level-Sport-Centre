package com.shsl.mapper;

import com.github.pagehelper.Page;
import com.shsl.annotation.AutoFill;
import com.shsl.dto.UserPageQueryDTO;
import com.shsl.dto.UserPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.entity.User;
import com.shsl.entity.User;
import com.shsl.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
/**
 * Manage User Information
 */

    /**
     * 1. Query users by userName
     * @param userName
     * @return User
     */
    @Select("select * from user where user_name = #{userName}")
    User getByUsername(String userName);

    /**
     * 2. Query users by openid
     * @param openid
     * @return User
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 3. Query users by user id
     * @param id
     * @return User
     */
    @Select("select * from user where user_id = #{id}")
    User getById(Integer id);

    /**
     * 4. Query all data
     * @return List<User>
     */
    @Select("SELECT * FROM user")
    List<User> selectAll();

    /**
     * 5. Paging query
     * @param userPageQueryDTO
     * @return Page<User>
     */
    Page<User> selectByPage(UserPageQueryDTO userPageQueryDTO);

    /**
     * 6. Use dynamic conditions to count the number of users
     * @param map
     * @return Integer
     */
    Integer countByMap(Map map);

    /**
     * 7. Insert user data
     * @param user
     */
    @Insert("insert into user (openid, user_name, phone, email, password, sex, avatar, create_time)values(#{openid},#{userName},#{phone},#{email},#{password},#{sex},#{avatar},#{createTime})")
    @AutoFill(value = OperationType.INSERT)
    void insert(User user);

    /**
     * 8. Dynamically modify properties based on the primary key
     * @param admin
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(User user);

    /**
     * 9. Change the user phone number
     * @param id, phone
     */
    @Update("update user set phone = #{phone} where user_id = #{id}")
    void updateUserPhone(Integer id, String phone);

    /**
     * 10. Change the user email
     * @param id, email
     */
    @Update("update user set email = #{email} where user_id = #{id}")
    void updateUserEmail(Integer id, String email);

    /**
     *  11. Change the user sex
     * @param id, sex
     */
    @Update("update user set sex = #{sex} where user_id = #{id}")
    void updateUserSex(Integer id, String sex);

    /**
     * 12. Change the user phone number
     * @param id, avatar
     */
    @Update("update user set avatar = #{avatar} where user_id = #{id}")
    void updateUserAvatar(Integer id, String avatar);

    /**
     * 13. Delete data
     * @param id
     */
    @Delete("delete from user where user_id = #{id}")
    void deleteUserById(Integer id);

    /**
     * 14. Delete multiple groups of data in batches
     * @param ids
     */
    void deleteUserByIds(@Param("ids") int[] ids);

    /**
     * 14. Find the count of username
     * @param userName
     */
    @Select("SELECT COUNT(*) FROM user WHERE user_name = #{userName}")
    int findUserNameCount(String userName);
}