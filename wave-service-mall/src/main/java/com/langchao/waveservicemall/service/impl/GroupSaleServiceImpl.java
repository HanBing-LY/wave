package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.exception.CommonException;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.GroupSaleMapper;
import com.langchao.waveservicemall.mapper.GroupSaleProductMapper;
import com.langchao.waveservicemall.mapper.GroupSaleProductSkuMapper;
import com.langchao.waveservicemall.pojo.GroupSale;
import com.langchao.waveservicemall.pojo.GroupSaleProductSku;
import com.langchao.waveservicemall.service.GroupSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @Title: GroupSaleServiceImpl
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class GroupSaleServiceImpl extends ServiceImpl<GroupSaleMapper, GroupSale> implements GroupSaleService {
    @Autowired
    private GroupSaleMapper groupSaleMapper;
    @Autowired
    private GroupSaleProductMapper groupSaleProductMapper;
    @Autowired
    private GroupSaleProductSkuMapper groupSaleProductSkuMapper;

    /**
     * @param flag
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     * @author liyuan
     * @Description 分页查询拼团控制
     */
    @Override
    public IPage list(Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        if (StringUtils.isNull(page) || page < 1) {
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if (StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE) {
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        List<GroupSale> flashSales = groupSaleMapper.list(flag, startTime, endTime);
        IPage<GroupSale> pageInfo = new Page<>(page, size);
        QueryWrapper<GroupSale> groupSaleQueryWrapper = new QueryWrapper<>();
        groupSaleQueryWrapper.eq("flag", flag);
        groupSaleQueryWrapper.gt("startTime", startTime);
        groupSaleQueryWrapper.lt("endTime", endTime);
        return groupSaleMapper.selectPage(pageInfo, groupSaleQueryWrapper);
    }

    /**
     * @param id
     * @author liyuan
     * @Description 删除拼团控制
     */
    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void delpl(String id) {
        List<Integer> ids = StringUtils.stringToIntegerList(id);
        List<GroupSaleProductSku> groupSaleProductSkus = groupSaleProductSkuMapper.selectByGroupSaleIds(ids);
        if (groupSaleProductSkus.size() != 0) {
//            throw new CommonException("有拼团活动开启,禁止删除");
        }
        groupSaleMapper.deleteByGroupSaleIds(ids);
        groupSaleProductMapper.deleteByGroupSaleIds(ids);
    }

    @Override
    public void addGroupSale(GroupSale groupSale) {
        if (StringUtils.isNull(groupSale) || StringUtils.isNull(groupSale.getFlag()) || StringUtils.isNull(groupSale.getStartTime()) || StringUtils.isNull(groupSale.getEndTime())) {
//            throw new CommonException("拼团控制对象为空");
        }
        groupSaleMapper.insert(groupSale);
    }

    @Override
    public void updateGroupSale(GroupSale groupSale) {
        if (StringUtils.isNull(groupSale) || StringUtils.isNull(groupSale.getId()) || StringUtils.isNull(groupSale.getFlag()) || StringUtils.isNull(groupSale.getStartTime()) || StringUtils.isNull(groupSale.getEndTime())) {
//            throw new CommonException("秒杀控制对象为空");
        }
        if (groupSale.getEndTime().before(groupSale.getStartTime())) {
//            throw new CommonException("结束时间必须晚于 开启时间");
        }
        GroupSale groupSale1 = groupSaleMapper.selectById(groupSale.getId());
        QueryWrapper<GroupSaleProductSku> groupSaleProductSkuQueryWrapper = new QueryWrapper<>();
        groupSaleProductSkuQueryWrapper.eq("groupSaleId", groupSale.getId());
        List<GroupSaleProductSku> groupSaleProductSkus = groupSaleProductSkuMapper.selectList(groupSaleProductSkuQueryWrapper);
        if (groupSaleProductSkus.size() > 0 && groupSale1.getStartTime().getTime() != groupSale.getStartTime().getTime()) {
//            throw new CommonException("已有活动开启,静止调整开启时间");
        }
        groupSaleMapper.updateById(groupSale);
    }

    @Override
    public GroupSale selectOne(Integer id) {
        if (StringUtils.isNull(id) || id < 1) {
            return new GroupSale();
        }
        return groupSaleMapper.selectById(id);
    }
}
