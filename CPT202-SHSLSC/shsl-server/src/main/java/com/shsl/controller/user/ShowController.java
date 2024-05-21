package com.shsl.controller.user;

import com.shsl.constant.JwtClaimsConstant;
import com.shsl.constant.MessageConstant;
import com.shsl.entity.Stadium;
import com.shsl.exception.TokenError;
import com.shsl.exception.TokenExpirationError;
import com.shsl.properties.JwtProperties;
import com.shsl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * User Management Controller Layer
 */
@Controller
@Slf4j
@RequestMapping("/")
@Api(tags = "Page Display Interface")
public class ShowController {

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/user/homepage")
    @ApiOperation(value = "User Homepage")
    public ModelAndView enterHomepage(@RequestParam(name = "Token", required = false) String token) {
        ModelAndView modelAndView = new ModelAndView("homepage");
        // 进行Token验证
        if(token!=null){
            try {
                Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
                // 验证Token是否有效，例如检查是否有必要的claims
                if (claims.getExpiration().before(new Date())) {
                    throw new TokenExpirationError(MessageConstant.TOKEN_EXPIRATION);
                }

                Integer userId = Integer.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
                log.info("Current user id：{}", userId);



            // 如果Token验证成功，则返回一个ModelAndView对象

            modelAndView.addObject("userId", userId);

            } catch (Exception e) {
                // 检查是否是JWT解析异常
                if (e instanceof SignatureException || e instanceof MalformedJwtException || e instanceof ExpiredJwtException) {
                    // 这些异常可能是JWT库抛出的，表明Token是伪造的、格式错误的或已过期
                    throw new TokenError(MessageConstant.TOKEN_ERROR);
                }
                // 如果是其他类型的异常，统一抛出token问题
                throw new RuntimeException(MessageConstant.TOKEN_ERROR);
            }


        } else {
            // 如果Token没有提供，则返回登录
            modelAndView.setViewName("redirect:/user/loginView"); // 重定向到登录页面
        }
        return modelAndView;
    }

}



