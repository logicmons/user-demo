package com.ysj.user.service;

import com.sun.javaws.jnl.IconDesc;
import com.ysj.user.mapper.UserMapper;
import com.ysj.user.pojo.User;
import com.ysj.user.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    public void register(User user, String code){
        if (!"1234".equals(code)){
            throw new RuntimeException("验证码错误或失效");
        }
        String salt = CodeUtils.generateSalt();
        user.setSalt(salt);
        //密码加盐
        user.setPassword(CodeUtils.md5Hex(user.getPassword(),salt));

        user.setId(null);
        user.setCreated(new Date());

        int insert = userMapper.insert(user);
        if (insert != 1){
            throw new RuntimeException("注册失败");
        }
    }

    public User query(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User user1 = userMapper.selectOne(user);
        boolean b = Objects.equals(CodeUtils.md5Hex(password, user1.getSalt()), user1.getPassword());
        if (b){
            return user1;
        }
        throw new RuntimeException("密码错误");
    }
}
