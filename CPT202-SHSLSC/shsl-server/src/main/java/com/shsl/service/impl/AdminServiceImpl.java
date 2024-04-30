package com.shsl.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shsl.constant.MessageConstant;
import com.shsl.constant.PasswordConstant;
import com.shsl.constant.PowerConstant;
import com.shsl.dto.AdminDTO;
import com.shsl.dto.AdminLoginDTO;
import com.shsl.dto.AdminPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.exception.AccountNotFoundException;
import com.shsl.exception.PasswordErrorException;
import com.shsl.mapper.AdminMapper;
import com.shsl.result.PageResult;
import com.shsl.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 员工登录
     *
     * @param adminLoginDTO
     * @return
     */
    @Override
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String adminName = adminLoginDTO.getAdminName();
        String password = adminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Admin admin = adminMapper.getByAdminName(adminName);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return admin;
    }
    

    /**
     * 新增员工
     *
     * @param adminDTO
     */
    @Override
    public void insert(AdminDTO adminDTO) {
        Admin admin = new Admin();

        //对象属性拷贝
        BeanUtils.copyProperties(adminDTO, admin);

        //设置账号权限等级，默认等级为1
        admin.setPower(PowerConstant.GRADE1);

        //设置密码，默认密码123456
        admin.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        admin.setCreateTime(LocalDateTime.now());

        adminMapper.insert(admin);
    }

    /**
     * 分页查询
     *
     * @param adminPageQueryDTO
     * @return
     */
    @Override
    public PageResult selectByPage(AdminPageQueryDTO adminPageQueryDTO) {
        // select * from admin limit 0,10
        //开始分页查询
        PageHelper.startPage(adminPageQueryDTO.getPage(), adminPageQueryDTO.getPageSize());

        Page<Admin> page = adminMapper.selectByPage(adminPageQueryDTO);

        long total = page.getTotal();
        List<Admin> records = page.getResult();

        return new PageResult(total, records);
    }


    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    /**
     * 编辑员工信息
     *
     * @param adminDTO
     */
    @Override
    public void update(AdminDTO adminDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO, admin);
        adminMapper.update(admin);
    }

    /**
     * 编辑员工信息
     *
     * @param adminDTO
     */
    @Override
    public void updateAdminPower(Integer power, Integer id) {
        adminMapper.updateAdminPower(power,id);
    }

    @Override
    public void deleteAdminById(AdminDTO adminDTO) {
        adminMapper.deleteAdminById(adminDTO.getAdminId());
    }

    @Override
    public void deleteAdminByIds(int[] ids) {
        adminMapper.deleteAdminByIds(ids);
    }
}