package com.shsl.interceptor;

import com.shsl.constant.JwtClaimsConstant;
import com.shsl.properties.JwtProperties;
import com.shsl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、Gets the token from the request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、Check token
        try {
            log.info(" Check jwt:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Integer adminId = Integer.valueOf(claims.get(JwtClaimsConstant.ADMIN_ID).toString());
            log.info("Current admin id：", adminId);
            //3、Pass, let him go
            return true;
        } catch (Exception ex) {
            //4、If no, the response is 401 status code
            response.setStatus(401);
            return false;
        }
    }
}