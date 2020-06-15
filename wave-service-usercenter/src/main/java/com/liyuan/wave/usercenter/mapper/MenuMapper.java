package com.liyuan.wave.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.po.ucenter.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> selectPermissionByUserId(Integer userId);

}
