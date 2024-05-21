package com.shsl.service;

import com.shsl.dto.AdminDTO;
import com.shsl.dto.AdminLoginDTO;
import com.shsl.dto.AdminPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.entity.User;
import com.shsl.result.PageResult;

import java.util.List;

public interface AdminService {

    /**
     * Admin log-in
     * @param adminLoginDTO
     * @return Admin
     */
    Admin login(AdminLoginDTO adminLoginDTO);

    /**
     * Query all records
     * @return List<User>
     */
    List<Admin> selectAll();

    /**
     * Paging query
     * @param adminPageQueryDTO
     * @return PageResult
     */
    PageResult selectByPage(AdminPageQueryDTO adminPageQueryDTO);

    /**
     * Query admin by id
     * @param id
     * @return Admin
     */
    Admin getById(Integer id);

    /**
     * Add new admin
     * @param adminDTO
     */
    void insert(AdminDTO adminDTO);

    /**
     * Edit admin information
     * @param adminDTO
     */
    void update(AdminDTO adminDTO);

    /**
     * Alter admin power
     * @param power
     * @param id
     */
    void updateAdminPower(Integer power, Integer id);


    /**
     * Delete admin by id
     * @param adminDTO
     */
    void deleteAdminById(AdminDTO adminDTO);

    /**
     * Delete in batches
     * @param ids
     */
    void deleteAdminByIds(int[] ids);
}