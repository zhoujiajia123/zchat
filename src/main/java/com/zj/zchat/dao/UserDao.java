package com.zj.zchat.dao;

import com.zj.zchat.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public List<User> getByMap();
    public User getByUsername(String username);
}
