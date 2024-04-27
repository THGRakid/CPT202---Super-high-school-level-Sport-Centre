package com.shsl.mapper;

import com.shsl.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Select("select * from user where user_name = #{userName}")
    User getByUserName(String userName);

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    //查询所有
    @Select("SELECT * FROM user")
    @ResultMap("userResultMap")
    List<User> selectAll();

    //添加数据
    @Insert("insert into user (openid, user_name, phone, email, sex, avatar, create_time)values(#{openid},#{userName},#{phone},#{email},#{sex},#{avatar},#{createTime})")
    void add(User user);

    //更新电话数据
    @Update("update user set phone = #{phone} where user_id = #{id}")
    void updateUserPhone(Integer id, String phone);

    //更新邮箱数据
    @Update("update user set email = #{email} where user_id = #{id}")
    void updateUserEmail(Integer id, String email);

    //更新性别数据
    @Update("update user set sex = #{sex} where user_id = #{id}")
    void updateUserSex(Integer id, String sex);

    //更新头像数据
    @Update("update user set avatar = #{avatar} where user_id = #{id}")
    void updateUserAvatar(Integer id, String avatar);

    //删除数据
    @Delete("delete from user where user_id = #{id}")
    void deleteUserById(Integer id);

    //分页查询
    @Select("select * from user limit #{begin},#{size}")
    @ResultMap("userResultMap")
    List<User> selectByPage(@Param("begin") int begin,@Param("size") int size);

    //查询总记录数
    @Select("select count(*) from user ")
    int selectTotalCount();

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询
    List<User> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("user") User user);

    //查询总记录数
    int selectTotalCountByCondition(User user);

}