package com.liyuan.wave.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.oms.client.UmsUserAddressClient;
import com.liyuan.wave.oms.mapper.OmsOrderInfoMapper;
import com.liyuan.wave.oms.mapper.OmsOrderProductMapper;
import com.liyuan.wave.oms.po.dto.OmsOrderInfoDto;
import com.liyuan.wave.oms.po.dto.OmsOrderProductDto;
import com.liyuan.wave.oms.service.OmsOrderInfoService;
import com.liyuan.wave.po.oms.OmsOrderInfo;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoDetailVo;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoSaveVo;
import com.liyuan.wave.po.oms.vo.OmsOrderProductVo;
import com.liyuan.wave.po.oms.vo.relation.OmsOrderInfoRelation;
import com.liyuan.wave.po.ums.vo.UmsUserAddressVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @description oms_order_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsOrderInfoService")
public class OmsOrderInfoServiceImpl extends ServiceImpl<OmsOrderInfoMapper, OmsOrderInfo> implements OmsOrderInfoService {

    @Autowired
    private UmsUserAddressClient umsUserAddressClient;

    @Resource
    private OmsOrderInfoMapper omsOrderInfoMapper;
    @Resource
    private OmsOrderProductMapper omsOrderProductMapper;

    @Override
    public OmsOrderInfoRelation getOrderDetailByOrderNumber(String orderNumber) {
        OmsOrderInfoDto omsOrderInfoDto = omsOrderInfoMapper.selectByOrderNumber(orderNumber);
        List<OmsOrderProductDto> omsOrderProductDtoList = omsOrderProductMapper.selectByOrderNumber(orderNumber);
        OmsOrderInfoDetailVo omsOrderInfoDetailVo = new OmsOrderInfoDetailVo();
        BeanUtils.copyProperties(omsOrderInfoDto, omsOrderInfoDetailVo);
        List<OmsOrderProductVo> collect = omsOrderProductDtoList.stream().map(i -> {
            OmsOrderProductVo omsOrderProductVo = new OmsOrderProductVo();
            BeanUtils.copyProperties(i, omsOrderProductVo);
            return omsOrderProductVo;
        }).collect(Collectors.toList());
        OmsOrderInfoRelation omsOrderInfoRelation = new OmsOrderInfoRelation();
        omsOrderInfoRelation.setOmsOrderInfoDetailVo(omsOrderInfoDetailVo);
        omsOrderInfoRelation.setOmsOrderProductVos(collect);
        return omsOrderInfoRelation;
    }

    @Override
    public void chooseReceiveAddress(String orderNumber, Long userAddressId) {
        OmsOrderInfoDto omsOrderInfoDto = omsOrderInfoMapper.selectByOrderNumber(orderNumber);
        JsonResult jsonResult = umsUserAddressClient.selectById(userAddressId);
        UmsUserAddressVo umsUserAddressVo = JSONObject.parseObject(jsonResult.toString(), UmsUserAddressVo.class);
        OmsOrderInfo omsOrderInfo = new OmsOrderInfo();
        BeanUtils.copyProperties(omsOrderInfoDto,omsOrderInfo);
        omsOrderInfo.setReceiveName(umsUserAddressVo.getUserName());
        omsOrderInfo.setReceivePhone(umsUserAddressVo.getUserPhone());
        omsOrderInfo.setProvince(umsUserAddressVo.getProvince());
        omsOrderInfo.setDistrict(umsUserAddressVo.getDistrict());
        omsOrderInfo.setArea(umsUserAddressVo.getArea());
        omsOrderInfo.setAddress(umsUserAddressVo.getAddress());
        omsOrderInfoMapper.updateById(omsOrderInfo);
    }

    @Override
    public String create(OmsOrderInfoSaveVo omsOrderInfoSaveVo) {
        return null;
    }

    @Override
    public void pay(String orderNumber) {

    }
}