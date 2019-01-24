package com.zj.zchat.service;

import com.zj.zchat.dao.FriendsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private FriendsDao friendsDao;
    public List<String> fgetByUsername(String username){
        return friendsDao.fgetByUsername(username);
    }
}
