package com.example.controller.user;

import com.example.constant.JwtClaimsConstant;
import com.example.dto.UserEnrollDTO;
import com.example.dto.UserLoginDTO;
import com.example.entity.User;
import com.example.properties.JwtProperties;
import com.example.result.Result;
import com.example.service.UserService;
import com.example.utils.JwtUtil;
import com.example.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@RestController
@RequestMapping("/user/user")
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

//        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 用户注册
     * @param userEnrollDTO
     * @return
     */
    @PostMapping("/enroll")
    public Result<String> enroll(@RequestBody UserEnrollDTO userEnrollDTO){
        Boolean flag = userService.enroll(userEnrollDTO);
        if (flag==true){
            return Result.success();
        }else {
            return Result.error("密码不一致");
        }

    }


    /**
     * 查询用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id){
        log.info("用户id",id);
        User user=userService.getById(id);
        return Result.success(user);
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
