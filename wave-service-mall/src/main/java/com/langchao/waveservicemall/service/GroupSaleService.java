package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.GroupSale;

import java.util.Date;

/**
 * @Title: GroupSaleService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface GroupSaleService extends IService<GroupSale> {

    /**
     * @author liyuan
     * @Description  分页查询拼团控制
     * @param flag
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
   IPage list(Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @author liyuan
     * @Description 删除拼团控制
     * @param id
     */
    void delpl(String id);

    /**
     * @author liyuan
     * @Description 添加拼团控制
     * @param groupSale
     */
    void addGroupSale(GroupSale groupSale);

    /**
     * @author liyuan
     * @Description 修改拼团控制
     * @param groupSale
     */
    void updateGroupSale(GroupSale groupSale);

    /**
     * @author liyuan
     * @Description 查询秒杀控制
     * @param id
     * @return
     */
    GroupSale selectOne(Integer id);

}
