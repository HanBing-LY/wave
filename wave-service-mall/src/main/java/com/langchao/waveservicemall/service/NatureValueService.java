package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author
 * @Title: NatureValueService
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface NatureValueService {

   IPage findManagerList(Integer id, Integer page, Integer size);

}
