package com.shsl.mapper;


import com.shsl.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名查询员工
     * @param adminName
     * @return
     */
    @Select("select * from admin where admin_name = #{adminName}")
    Admin getByAdminName(String adminName);

}
