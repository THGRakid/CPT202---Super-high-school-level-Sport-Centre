package com.shsl.controller.user;

import com.alibaba.fastjson.JSON;
import com.shsl.constant.JwtClaimsConstant;
import com.shsl.constant.MessageConstant;
import com.shsl.dto.UserDTO;
import com.shsl.dto.UserLoginDTO;
import com.shsl.dto.UserPageQueryDTO;
import com.shsl.dto.UserRegisterDTO;
import com.shsl.entity.User;
import com.shsl.exception.CodeErrorException;
import com.shsl.properties.JwtProperties;
import com.shsl.result.PageResult;
import com.shsl.result.Result;
import com.shsl.service.UserService;
import com.shsl.utils.JwtUtil;
import com.shsl.utils.VerifyCodeUtils;
import com.shsl.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User Management Controller Layer
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "User Network Interface")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 1. User Log-in Interface
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

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 2. Generate verification code
     */
    @RequestMapping("generateImageCode")
    @ApiOperation(value = "Generate Image Code")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        //1. Generate 4-digit random numbers
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //2. Save to the session scope
        session.setAttribute("code", code);
        //3. Generate picture according to a random number
        //4. Respond to the picture with response
        //5. Set the response type
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(220, 60, os, code);
    }

    /**
     * 3. User Log-out Interface
     *
     * @return Result<String>
     */
    @PostMapping("/logout")
    @ApiOperation(value = "User Log-out")
    public String logout(HttpSession session) {
        session.invalidate(); //session invalid
        return "redirect:/init"; //Go back to the login page
    }

    /**
     * 4. User Register Interface
     *
     * @param userRegisterDTO
     * @return Result<UserLoginVO>
     */
    @PostMapping("/register")
    @ApiOperation(value = "User Register")
    public Result Register(@RequestBody UserRegisterDTO userRegisterDTO, HttpSession session) {

        log.debug("Add New User:  Username: {}, Password: {}, Email: {},",
                userRegisterDTO.getUserName(), userRegisterDTO.getPassword(), userRegisterDTO.getEmail());
        log.debug("The user enters the verification code: {}", userRegisterDTO.getCode());

        String code = userRegisterDTO.getCode();

        //1.判断用户输入验证码和session中验证码是否一致
        String sessionCode = session.getAttribute("code").toString();
        if (!sessionCode.equalsIgnoreCase(code)) throw new CodeErrorException(MessageConstant.CODE_ERROR);
        //2.注册用户
        userService.insert(userRegisterDTO);
        return Result.success();
    }


    /**
     * 用户分页查询
     *
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("用户分页查询")
    public Result<PageResult> page(UserPageQueryDTO userPageQueryDTO) {
        log.info("用户分页查询，参数为：{}", userPageQueryDTO);
        PageResult pageResult = userService.selectByPage(userPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户信息")
    public Result<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }


    /**
     * 编辑用户信息
     *
     * @param userDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("编辑用户信息")
    public Result update(@RequestBody UserDTO userDTO) {
        log.info("编辑用户信息：{}", userDTO);
        userService.update(userDTO);
        return Result.success();
    }


    /**
     * 删除用户
     *
     * @param userDTO
     * @return
     */
    @PutMapping("/deleteById")
    @ApiOperation("删除用户")
    public Result deleteById(@RequestBody UserDTO userDTO) {
        log.info("删除用户：{}", userDTO.getUserName());
        userService.deleteUserById(userDTO);
        return Result.success();
    }


    /**
     * 批量删除用户
     *
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
