package com.zj.zchat.service;

import com.zj.zchat.dao.UserDao;
import com.zj.zchat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User getUserByUsername(String username){
      return userDao.getByUsername(username);
    }
}
