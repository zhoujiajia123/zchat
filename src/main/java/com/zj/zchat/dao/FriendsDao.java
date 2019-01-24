package com.zj.zchat.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendsDao {
    public List<String> fgetByUsername(String username);
}
