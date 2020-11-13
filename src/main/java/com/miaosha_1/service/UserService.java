package com.miaosha_1.service;

import com.miaosha_1.dao.UserDao;
import com.miaosha_1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qqtang
 * @Date 2020/11/13 17:19
 * @Desc
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getById(Integer id){
        return userDao.getById(id);
    }
}
