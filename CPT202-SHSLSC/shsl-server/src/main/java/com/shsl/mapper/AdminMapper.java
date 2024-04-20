package com.shsl.mapper;


import com.shsl.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 根据员工查询员工
     * @param adminName
     * @return
     */
    @Select("select * from admin where adminName = #{adminName}")
    Admin getByAdminName(String adminName);

}
