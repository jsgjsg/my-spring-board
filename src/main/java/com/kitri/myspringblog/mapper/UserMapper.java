package com.kitri.myspringblog.mapper;

import com.kitri.myspringblog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users(username, password, enabled) VALUES (#{username}, #{password}, #{enabled})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Insert("INSERT INTO authorities (user_id, authority) VALUES (#{userId}, #{authority})")
    void insertAuthority(Long userId, String authority);

}
