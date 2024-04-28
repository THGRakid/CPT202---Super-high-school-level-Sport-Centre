package com.shsl.controller.user;

import com.shsl.constant.JwtClaimsConstant;
import com.shsl.dto.WeChatLoginDTO;
import com.shsl.entity.User;
import com.shsl.properties.JwtProperties;
import com.shsl.result.Result;
import com.shsl.service.UserService;
import com.shsl.utils.JwtUtil;
import com.shsl.vo.WeChatLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/wechat")
@Api(tags = "C端用户相关接口")
@Slf4j
public class WeChatLoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 微信登录
     * @param weChatLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("微信登录")
    public Result<WeChatLoginVO> login(@RequestBody WeChatLoginDTO weChatLoginDTO){
        log.info("微信用户登录：{}",weChatLoginDTO.getCode());

        //微信登录
        User user = userService.wxLogin(weChatLoginDTO);

        //为微信用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getUserId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        WeChatLoginVO weChatLoginVO = WeChatLoginVO.builder()
                .userid(user.getUserId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(weChatLoginVO);
    }
}
