package com.shsl.mapper;

import com.github.pagehelper.Page;
import com.shsl.annotation.AutoFill;
import com.shsl.dto.AdminPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.enumeration.OperationType;
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
    List<Admin> selectAll();

    /**
     * 插入员工数据
     * @param admin
     */
    @Insert("insert into admin values(null,#{adminName},#{depName},null,#{phone},#{email},#{createTime},#{expirationTime},#{power})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Admin admin);


    /**
     * 分页查询
     * @param adminPageQueryDTO
     * @return
     */
    Page<Admin> selectByPage(AdminPageQueryDTO adminPageQueryDTO);

    /**
     * 根据主键动态修改属性
     * @param admin
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Admin admin);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Select("select * from admin where admin_id = #{id}")
    Admin getById(Integer id);


    //更新数据
    @Update("update admin set power = #{power} where admin_id = #{id}")
    void updateAdminPower(Integer id, Integer power);

    //删除数据
    @Delete("delete from admin where admin_id = #{id}")
    void deleteAdminById(Integer id);

    //批量删除
    void deleteAdminByIds(@Param("ids") int[] ids);

}
