package com.shsl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.shsl.dto.AdminDTO;
import com.shsl.dto.AdminLoginDTO;
import com.shsl.dto.AdminPageQueryDTO;
import com.shsl.entity.Admin;
import com.shsl.result.PageResult;
import com.shsl.service.AdminService;
import com.shsl.utils.JwtUtil;
import com.shsl.vo.AdminLoginVO;
import com.shsl.constant.JwtClaimsConstant;
import com.shsl.properties.JwtProperties;
import com.shsl.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/admin")
@Slf4j
@Api(tags = "管理员相关接口")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param adminLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("员工登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getAdminId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .token(token)
                .build();

        return Result.success(adminLoginVO);
    }

    
    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "管理员登出")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * 新增员工
     * @param adminDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增员工")
    public Result insert(@RequestBody AdminDTO adminDTO){
        log.info("新增员工：{}",adminDTO);
        adminService.insert(adminDTO);
        return Result.success();
    }


    /**
     * 员工分页查询
     * @param adminPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(AdminPageQueryDTO adminPageQueryDTO){
        log.info("员工分页查询，参数为：{}", adminPageQueryDTO);
        PageResult pageResult = adminService.selectByPage(adminPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public Result<Admin> getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }


    /**
     * 编辑员工信息
     * @param adminDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody AdminDTO adminDTO){
        log.info("编辑员工信息：{}", adminDTO);
        adminService.update(adminDTO);
        return Result.success();
    }


    /**
     * 编辑员工权限
     * @param adminDTO
     * @return
     */
    @PutMapping("/power")
    @ApiOperation("编辑员工权限")
    public Result updateAdminPower(@RequestBody AdminDTO adminDTO){
        log.info("编辑员工权限：{}", adminDTO.getAdminName());
        Integer id = adminDTO.getAdminId();
        Integer power = adminDTO.getPower();
        adminService.updateAdminPower(power, id);
        return Result.success();
    }
    /**
     * 删除员工
     * @param adminDTO
     * @return
     */
    @PutMapping("/deleteById")
    @ApiOperation("删除员工")
    public Result deleteById(@RequestBody AdminDTO adminDTO){
        log.info("删除员工：{}", adminDTO.getAdminName());
        adminService.deleteAdminById(adminDTO);
        return Result.success();
    }


    /**
     * 批量删除员工
     * @param request
     * @return
     */
    @PutMapping("/deleteByIds")
    @ApiOperation("批量删除员工")
    public Result deleteByIds(HttpServletRequest request) throws IOException {
        //1、接收数据 JSON字符串格式 [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2、转为int[]
        int[] ids = JSON.parseObject(params, int[].class);
        log.info("批量删除员工：{}", ids);
        adminService.deleteAdminByIds(ids);
        return Result.success();
    }

}
