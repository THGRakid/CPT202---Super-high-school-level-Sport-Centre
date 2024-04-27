package com.shsl.mapper;

import com.shsl.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    /**
     * 管理员工信息
     */

    //根据员工名查询员工
    @Select("select * from admin where admin_name = #{adminName}")
    Admin getByAdminName(String adminName);

    //查询所有
    @Select("SELECT * FROM admin")
    @ResultMap("adminResultMap")
    List<Admin> selectAll();

    //添加数据
    @Insert("insert into admin values(null,#{adminName},#{depName},null,#{phone},#{email},#{createTime},#{expirationTime},1)")
    void add(Admin admin);

    //更新数据
    @Update("update admin set power = #{power} where admin_id = #{id}")
    void updateAdminPower(Integer id, Integer power);

    //删除数据
    @Delete("delete from admin where admin_id = #{id}")
    void deleteAdminById(Integer id);

    //分页查询
    @Select("select * from admin limit #{begin},#{size}")
    @ResultMap("adminResultMap")
    List<Admin> selectByPage(@Param("begin") int begin,@Param("size") int size);

    //查询总记录数
    @Select("select count(*) from admin ")
    int selectTotalCount();

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询
    List<Admin> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("admin") Admin admin);

    //查询总记录数
    int selectTotalCountByCondition(Admin admin);

}
