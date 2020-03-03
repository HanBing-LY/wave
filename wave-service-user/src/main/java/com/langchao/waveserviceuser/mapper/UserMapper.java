package com.langchao.waveserviceuser.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.wavepo.user.User;

public interface UserMapper extends BaseMapper<User> {
	String updateUser(User user);
}
