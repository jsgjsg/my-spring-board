package com.kitri.myspringblog.service;

import com.kitri.myspringblog.domain.User;
import com.kitri.myspringblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    public void signup(User user) {
        if(!user.getPassword().equals(user.getPassword2())) throw new RuntimeException("비밀번호 일치 X");

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(true);

        userMapper.insert(user);
        userMapper.insertAuthority(user.getId(), "ROLE_USER");
    }

}
