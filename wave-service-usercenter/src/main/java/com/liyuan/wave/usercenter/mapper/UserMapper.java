package com.liyuan.wave.usercenter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.po.user.User;

public interface UserMapper extends BaseMapper<User> {
	String updateUser(User user);
}
