package com.miaosha_1.service;

import com.miaosha_1.dao.MiaoshaUserDao;
import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.eception.GlobalException;
import com.miaosha_1.redis.MiaoShaUserKey;
import com.miaosha_1.result.CodeMsg;
import com.miaosha_1.util.MD5Util;
import com.miaosha_1.util.UUIDUtil;
import com.miaosha_1.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qqtang
 * @Date 2020/11/17 16:17
 * @Desc
 */
@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getById(Long id) {
        MiaoshaUser user = redisService.get(MiaoShaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null){
            return user;
        }
        user = miaoshaUserDao.getById(id);
        if (user != null){
            redisService.set(MiaoShaUserKey.getById, "" + id, user);
        }
        return user;
    }

    //更改密码，这个用不到，但实际开发会用到
    public boolean updatePassword(Long id,String passwordNew,String token){
        MiaoshaUser user = getById(id);
        if (user == null){
            return false;
        }
        //更改数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.fromPassToDBPass(passwordNew,user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        //更新缓存
        redisService.delete(MiaoShaUserKey.getById, "" + id);
        user.setPassword(passwordNew);
        redisService.set(MiaoShaUserKey.token,token,user);
        return true;
    }
    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoShaUserKey.token, token, MiaoshaUser.class);
        if (user != null){
            //延长有效期
            addCookie(response,user,token);
        }
        return user;
    }

    public Boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //验证手机号
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOTEXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        String calcPass = MD5Util.fromPassToDBPass(password, salt);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response,user,token);
        return true;
    }
    private void addCookie(HttpServletResponse response,MiaoshaUser user,String token){
        redisService.set(MiaoShaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserKey.token.expireSecond());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
