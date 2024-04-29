package com.shsl.service;

import com.shsl.dto.AdminDTO;
import com.shsl.dto.AdminLoginDTO;
import com.shsl.dto.AdminPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.result.PageResult;

public interface AdminService {

    /**
     * 管理员登录
     * @param adminLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO adminLoginDTO);

    /**
     * 新增员工
     * @param adminDTO
     */
    void insert(AdminDTO adminDTO);

    /**
     * 分页查询
     * @param adminPageQueryDTO
     * @return
     */
    PageResult selectByPage(AdminPageQueryDTO adminPageQueryDTO);

    /**
     * 更改员工权限
     * @param power
     * @param id
     */
    void updateAdminPower(Integer power, Integer id);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Admin getById(Integer id);

    /**
     * 编辑员工信息
     * @param adminDTO
     */
    void update(AdminDTO adminDTO);

    /**
     * 删除员工
     * @param adminDTO
     */
    void deleteAdminById(AdminDTO adminDTO);

    //批量删除
    void deleteAdminByIds(int[] ids);
}