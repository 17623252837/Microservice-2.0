package com.hrh.micro.business.controller;

import com.google.common.collect.Maps;
import com.hrh.micro.business.dto.LoginInfo;
import com.hrh.micro.business.dto.LoginParam;
import com.hrh.micro.business.feign.ProfileFeign;
import com.hrh.micro.commons.dto.com.hrh.ResponseResult;
import com.hrh.micro.commons.utils.MapperUtils;
import com.hrh.micro.commons.utils.OkHttpClientUtil;
import com.hrh.micro.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.controller
 * @ClassName: LoginController
 * @Author: HuRonghua
 * @Description: 登录接口
 * @Date: 2020/3/20 23:20
 * @Version: 1.0
 */
@RestController
public class LoginController {
    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name = "tokenStore")
    public TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @PostMapping("/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam) {
        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if(userDetails == null ||  !bCryptPasswordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())) {
            return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.FAIL,"账号或者密码错误",null);
        }

        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();
        Map<String, String> params = new HashMap<>();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = response.body().string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK, "登录成功",result);
    }

    /**
     * 获取用户信息
     * @param httpServletRequest {@link HttpServletRequest}
     * @return
     */
    @GetMapping("/user/info")
    public ResponseResult<LoginInfo> info(HttpServletRequest httpServletRequest){
        // 获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        String jsonInfo = profileFeign.info(name);
        LoginInfo loginInfo = null;
        try {
            UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonInfo, "data", UmsAdmin.class);
            loginInfo = new LoginInfo();
            loginInfo.setName(umsAdmin.getUsername());
            loginInfo.setAvatar(umsAdmin.getIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"获取用户信息",loginInfo);
    }

    @PostMapping("/user/logout")
    public ResponseResult<Void> logOut(HttpServletRequest httpServletRequest){
        String access_token = httpServletRequest.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"退出登录",null);
    }
}
