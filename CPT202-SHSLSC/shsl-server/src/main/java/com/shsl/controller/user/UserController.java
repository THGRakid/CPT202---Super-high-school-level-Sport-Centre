package com.shsl.controller.user;

import com.alibaba.fastjson.JSON;
import com.shsl.dto.UserDTO;
import com.shsl.dto.UserPageQueryDTO;
import com.shsl.dto.UserLoginDTO;
import com.shsl.entity.User;
import com.shsl.entity.User;
import com.shsl.result.PageResult;
import com.shsl.service.UserService;
import com.shsl.utils.JwtUtil;
import com.shsl.vo.UserLoginVO;
import com.shsl.constant.JwtClaimsConstant;
import com.shsl.properties.JwtProperties;
import com.shsl.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User Management Controller Layer
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
@Api(tags = "User Network Interface")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * User Log-in Interface
     *
     * @param userLoginDTO
     * @return Result<UserLoginVO>
     */
    @PostMapping("/login")
    @ApiOperation(value = "User Log-in")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("User Log-in：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //After successful login, the jwt token is generated
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO employeeLoginVO = UserLoginVO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * User Log-out Interface
     *
     * @return Result<String>
     */
    @PostMapping("/logout")
    @ApiOperation(value = "User Log-out")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增用户")
    public Result insert(@RequestBody UserDTO userDTO){
        log.info("新增用户：{}",userDTO);
        userService.add(userDTO);
        return Result.success();
    }


    /**
     * 用户分页查询
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("用户分页查询")
    public Result<PageResult> page(UserPageQueryDTO userPageQueryDTO){
        log.info("用户分页查询，参数为：{}", userPageQueryDTO);
        PageResult pageResult = userService.selectByPage(userPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result<User> getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }


    /**
     * 编辑用户信息
     * @param userDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("编辑用户信息")
    public Result update(@RequestBody UserDTO userDTO){
        log.info("编辑用户信息：{}", userDTO);
        userService.update(userDTO);
        return Result.success();
    }


    /**
     * 删除用户
     * @param userDTO
     * @return
     */
    @PutMapping("/deleteById")
    @ApiOperation("删除用户")
    public Result deleteById(@RequestBody UserDTO userDTO){
        log.info("删除用户：{}", userDTO.getUserName());
        userService.deleteUserById(userDTO);
        return Result.success();
    }


    /**
     * 批量删除用户
     * @param request
     * @return
     */
    @PutMapping("/deleteByIds")
    @ApiOperation("批量删除用户")
    public Result deleteByIds(HttpServletRequest request) throws IOException {
        //1、接收数据 JSON字符串格式 [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//JSON字符串

        //2、转为int[]
        int[] ids = JSON.parseObject(params, int[].class);
        log.info("批量删除用户：{}", ids);
        userService.deleteUserByIds(ids);
        return Result.success();
    }


}
