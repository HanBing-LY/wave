package com.liyuan.wave.pms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.pms.mapper.PmsCommentDao;
import com.liyuan.wave.pms.service.PmsCommentService;
import com.liyuan.wave.po.entity.pms.PmsCommentEntity;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Service("pmsCommentService")
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentDao, PmsCommentEntity> implements PmsCommentService {

}