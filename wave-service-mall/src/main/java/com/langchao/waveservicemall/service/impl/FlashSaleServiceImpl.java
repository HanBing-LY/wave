package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.exception.CommonException;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.FlashSaleMapper;
import com.langchao.waveservicemall.mapper.FlashSaleProductMapper;
import com.langchao.waveservicemall.mapper.FlashSaleProductSkuMapper;
import com.langchao.waveservicemall.pojo.FlashSale;
import com.langchao.waveservicemall.pojo.FlashSaleProductSku;
import com.langchao.waveservicemall.service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Title: FlashSaleServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author
 * @date Mon Feb 17 16:34:43 CST 2020
 */
@Service
public class FlashSaleServiceImpl extends ServiceImpl<FlashSaleMapper, FlashSale> implements FlashSaleService {
    @Autowired
    private FlashSaleMapper flashSaleMapper;
    @Autowired
    private FlashSaleProductMapper flashSaleProductMapper;
    @Autowired
    private FlashSaleProductSkuMapper flashSaleProductSkuMapper;
    /**
     * @Author liyuan
     * @Description 添加秒杀控制
     * @param flashSale
     */
    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void addFlashSale(FlashSale flashSale) {
        if(StringUtils.isNull(flashSale) || StringUtils.isNull(flashSale.getFlag()) || StringUtils.isNull(flashSale.getStartTime()) || StringUtils.isNull(flashSale.getEndTime())){
            // TODO ("秒杀控制对象为空");
        }
        flashSaleMapper.insert(flashSale);
    }

    /**
     * @Author liyuan
     * @Description 修改秒杀控制
     * @param flashSale
     */
    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void updateFlashSale(FlashSale flashSale) {
        if(StringUtils.isNull(flashSale) || StringUtils.isNull(flashSale.getId()) || StringUtils.isNull(flashSale.getFlag()) || StringUtils.isNull(flashSale.getStartTime()) || StringUtils.isNull(flashSale.getEndTime())){
            // TODO 秒杀控制对象为空
        }
        if(flashSale.getEndTime().before(flashSale.getStartTime())){
            // TODO  结束时间必须晚于 开启时间
        }
        FlashSale flashSale1 = flashSaleMapper.selectById(flashSale.getId());
        QueryWrapper<FlashSaleProductSku> flashSaleQueryWrapper = new QueryWrapper<>();
        flashSaleQueryWrapper.eq("flashSaleId",flashSale.getId());
        List<FlashSaleProductSku> flashSaleProductSkus = flashSaleProductSkuMapper.selectList(flashSaleQueryWrapper);
        if(flashSaleProductSkus.size() > 0 && flashSale1.getStartTime().getTime() != flashSale.getStartTime().getTime()){
            // TODO 已有活动开启,静止调整开启时间
        }
        flashSaleMapper.updateById(flashSale);
    }

    /**
     * @author liyuan
     * @Description  分页查询所有秒杀控制
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<FlashSale> list(Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        if(StringUtils.isNull(page) || page < 1){
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE){
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        return flashSaleMapper.selectPage(new Page<FlashSale>(page,size),new QueryWrapper<FlashSale>());
    }

    /**
     * @author liyuan
     * @Description 查询秒杀控制
     * @param id
     * @return
     */
    @Override
    public FlashSale selectOne(Integer id) {
        if(StringUtils.isNull(id) || id < 1){
            return  new FlashSale() ;
        }
        return flashSaleMapper.selectById(id);
    }

    /**
     * @author liyuan
     * @Description 删除秒杀
     * @param checkIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delpl(String checkIds) {
        List<Integer> ids = StringUtils.stringToIntegerList(checkIds);
        List<FlashSaleProductSku> flashSaleProductSkus = flashSaleProductSkuMapper.selectByFlashSaleIds(ids);
        if(flashSaleProductSkus.size() != 0){
//            throw  new CommonException("有拼团活动开启,禁止删除");
        }
        flashSaleMapper.deleteByFlashSaleIds(ids);
        flashSaleProductMapper.deleteByFlashSaleIds(ids);
    }
}
