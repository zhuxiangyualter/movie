package com.example.controller;

import com.example.constant.JwtClaimsConstant;
import com.example.dto.UserLoginDTO;
import com.example.entity.User;
import com.example.properties.JwtProperties;
import com.example.result.Result;
import com.example.service.UserService;
import com.example.utils.JwtUtil;
import com.example.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        //登录成功后，生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
//        String token = JwtUtil.createJWT(
//                jwtProperties.getAdminSecretKey(),
//                jwtProperties.getAdminTtl(),
//                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
}
