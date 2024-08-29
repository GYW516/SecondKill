package com.gyw.secondkill.controller;

import com.gyw.secondkill.redis.RedisService;
import com.gyw.secondkill.result.Result;
import com.gyw.secondkill.service.MiaoshaUserService;
import com.gyw.secondkill.vo.LoginVo;
import com.gyw.secondkill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Dell
 * @create 2019-07-14 0:17
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    public static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, LoginVo loginVo) {
        log.info(loginVo.toString());
        //参数校验
        /*String password = loginVo.getPassword();
        String mobie = loginVo.getMobile();
        if (StringUtils.isEmpty(password))
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        if (StringUtils.isEmpty(mobie))
            return Result.error(CodeMsg.MOBILE_EMPTY);
        if (ValidatorUtil.isMobile(mobie))
            return Result.error(CodeMsg.MOBILE_ERROR);*/

        // 登录
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }

}
