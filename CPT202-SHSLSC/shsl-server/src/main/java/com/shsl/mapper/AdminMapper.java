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
 * Manage Admin Information
 */

    /**
     * 1. Query admins by admin name
     * @param adminName
     * @return Admin
     */
    @Select("select * from admin where admin_name = #{adminName}")
    Admin getByAdminName(String adminName);

    /**
     * 2. Query admins by admin id
     * @param id
     * @return Admin
     */
    @Select("select * from admin where admin_id = #{id}")
    Admin getById(Integer id);

    /**
     * 3. Query all data
     * @return List<Admin>
     */
    @Select("SELECT * FROM admin")
    List<Admin> selectAll();

    /**
     * 4. Paging query
     * @param adminPageQueryDTO
     * @return Page<Admin>
     */
    Page<Admin> selectByPage(AdminPageQueryDTO adminPageQueryDTO);

    /**
     * 5. Insert admin data
     * @param admin
     */
    @Insert("insert into admin values(null,#{adminName},#{depName},null,#{phone},#{email},#{createTime},#{expirationTime},#{power})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Admin admin);

    /**
     * 6. Dynamically modify properties based on the primary key
     * @param admin
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Admin admin);

    /**
     * 7. Change the admin permission level
     * @param id, power
     */
    @Update("update admin set power = #{power} where admin_id = #{id}")
    void updateAdminPower(Integer id, Integer power);

    /**
     * 8. Delete data
     * @param id
     */
    @Delete("delete from admin where admin_id = #{id}")
    void deleteAdminById(Integer id);

    /**
     * 8. Delete multiple groups of data in batches
     * @param ids
     */
    void deleteAdminByIds(@Param("ids") int[] ids);

}
