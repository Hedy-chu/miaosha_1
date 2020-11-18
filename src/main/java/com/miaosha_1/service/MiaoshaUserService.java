package com.miaosha_1.service;

import com.miaosha_1.dao.MiaoshaUserDao;
import com.miaosha_1.domain.MiaoshaUser;
import com.miaosha_1.result.CodeMsg;
import com.miaosha_1.util.MD5Util;
import com.miaosha_1.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qqtang
 * @Date 2020/11/17 16:17
 * @Desc
 */
@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(Long id){
        MiaoshaUser miaoshaUser = miaoshaUserDao.getById(id);
        return  miaoshaUser;
    }

    public CodeMsg login(LoginVo loginVo){
        if(loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //验证手机号
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null){
            return CodeMsg.MOBILE_NOTEXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String salt = user.getSalt();
        String calcPass = MD5Util.fromPassToDBPass(password, salt);
        if (calcPass.equals(dbPass)){
            return CodeMsg.SUCCESS;
        }
        return CodeMsg.PASSWORD_ERROR;
    }
}
