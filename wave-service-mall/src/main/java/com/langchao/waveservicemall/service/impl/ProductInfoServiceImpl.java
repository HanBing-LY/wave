package com.langchao.waveservicemall.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.*;
import com.langchao.waveservicemall.pojo.FlashSale;
import com.langchao.waveservicemall.pojo.GroupSale;
import com.langchao.waveservicemall.pojo.GroupSaleProduct;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.pojo.dto.FlashSaleProductDTO;
import com.langchao.waveservicemall.pojo.dto.ProductInfoDTO;
import com.langchao.waveservicemall.pojo.vo.ColumnProductVo;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductVo;
import com.langchao.waveservicemall.pojo.vo.ScoreProductInfoVo;
import com.langchao.waveservicemall.service.ProductColumnService;
import com.langchao.waveservicemall.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @Title: ProductInfoServiceImpl
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private GroupSaleProductMapper groupSaleProductMapper;
    @Autowired
    private GroupSaleMapper groupSaleMapper;
    @Autowired
    private ProductEvaluateMapper productEvaluateMapper;
    @Autowired
    private GroupInfoMapper groupInfoMapper;
    @Autowired
    private FlashSaleProductMapper flashSaleProductMapper;
    @Autowired
    private ProductColumnMapper productColumnMapper;
    @Autowired
    private ProductColumnService productColumnService;
    @Autowired
    private FlashSaleMapper flashSaleMapper;
    @Autowired
    private FlashSaleProductSkuMapper flashSaleProductSkuMapper;


    @Override
    public List<ProductInfo> getCollectListByUserId(Integer userId) {
        return productInfoMapper.getCollectListByUserId(userId);
    }

    /**
     * @param map
     * @return java.util.List<com.chemguan.entity.ProductInfo>
     * @Author zzz
     * @Description //TODO 4-0-7 负责人权益-商品提成比例
     * @Date 15:08 2020/3/11
     **/
    @Override
    public List<ProductInfo> getList(Map map) {
        return productInfoMapper.getList(map);
    }

    /**
     * 1-0-0查询热销商品
     *
     * @return
     * @Author liyuan
     */
    @Override
    public IPage listHotSaleList(Integer pageNum, Integer pageSize) {
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.SHOP_HOMEPAGE_HOT_DEFAULT_PAGE_SIZE;
        }
        IPage iPage = new Page<>(pageNum,pageSize);
//        热销商品首页展示6个
        QueryWrapper<ProductInfo> productInfoQueryWrapper = new QueryWrapper<>();
//        上架状态:1
        productInfoQueryWrapper.eq("type", 1);
//        销量倒序
        productInfoQueryWrapper.orderByDesc("saleNum");
//        同销量排序规则
        productInfoQueryWrapper.orderByDesc("orderVal");
        IPage productInfoIPage = productInfoMapper.selectPage(iPage, productInfoQueryWrapper);
        List<ProductInfo> productInfos = productInfoIPage.getRecords();
        ArrayList<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfo p : productInfos) {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(p, productInfoDTO);
            Integer integer = productInfoMapper.judgeProductGroupType(p.getId());
//            integer返回值为0,没有拼团记录
            productInfoDTO.setGroupType(integer == 0 ? 0 : 1);
//            如果没有拼团记录,查秒杀记录
            if (integer == 0) {
                Integer judgeProductFlashType = productInfoMapper.judgeProductFlashType(p.getId());
                productInfoDTO.setFlashType(judgeProductFlashType == 0 ? 0 : 1);
            }
            productInfoDTOS.add(productInfoDTO);
        }
        productInfoIPage.setRecords(productInfos);
        return productInfoIPage;
    }

    /**
     * 1-0-0查询最新商品
     *
     * @return
     * @Author liyuan
     */
    @Override
    public IPage listNewPushList(Integer pageNum, Integer pageSize) {
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.SHOP_HOMEPAGE_NEW_DEFAULT_PAGE_SIZE;
        }
        //        首页最新上架展示6个
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(ProductInfo.class);
        Example.Criteria criteria = condition.createCriteria();
//        上架状态:1
        criteria.andEqualTo("type", 1);
//        上架时间倒序
        condition.orderBy("pushTime").desc();
//        上架时间排序规则
        condition.orderBy("orderVal").desc();
        List<ProductInfo> productInfos = productInfoMapper.selectByCondition(condition);
//        构建前端交互集合
        ArrayList<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfo p : productInfos) {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(p, productInfoDTO);
            Integer integer = productInfoMapper.judgeProductGroupType(p.getId());
//            integer返回值为0,没有拼团记录
            productInfoDTO.setGroupType(integer == 0 ? 0 : 1);
            //            如果没有拼团记录,查秒杀记录
            if (integer == 0) {
                Integer judgeProductFlashType = productInfoMapper.judgeProductFlashType(p.getId());
                productInfoDTO.setFlashType(judgeProductFlashType == 0 ? 0 : 1);
            }
            productInfoDTOS.add(productInfoDTO);
        }
       IPage pageInfo = newIPage(productInfoDTOS);
        return pageInfo;
    }

    /**
     * @param orderType 0:默认，按照商品排序值排序
     *                  1:按商品销量由高到低排序
     *                  2:按照商品销量由低到高排序
     *                  3:按照价格由高到低排序
     *                  4:按照价格由低到高排序
     * @return
     * @Author liyuan
     * 1-0-1-0-0 俱乐部拼团 参与拼团列表,返回6条数据
     */
    @Override
    public IPage listGroupSaleList(Integer orderType, Integer pageNum, Integer pageSize) {
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.PRODUCT_CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE;
        }
//        判空
        orderType = Optional.ofNullable(orderType).orElse(0);
        String groupColumn = "";
        String groupEsc = "";
        if (orderType == 1 || orderType == 3) {
            groupEsc = "desc";
        }
        if (orderType == 1 || orderType == 2) {
            groupColumn = "sale_num";
        } else if (orderType == 3 || orderType == 4) {
            groupColumn = "product_sale_price";
        }
//        查询所有拼团id(非超级拼团)
        List<Integer> groupSaleProductIds = productInfoMapper.listGetAllGroupSaleIds(0);
//        groupSaleProductIds去重,除null
        groupSaleProductIds = groupSaleProductIds.stream().distinct().collect(Collectors.toList());
        groupSaleProductIds = groupSaleProductIds.stream().filter(i -> i != null).collect(Collectors.toList());
        if (groupSaleProductIds.size() == 0) {
//            集合为空,返回空集合
            return newIPage(Arrays.asList());
        }
//        启动分页
        PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> productInfos = productInfoMapper.listGetProductsByIds(groupSaleProductIds, groupColumn, groupEsc);
//        构建前端交互集合
        ArrayList<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfo p : productInfos) {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(p, productInfoDTO);
            Condition condition = new Condition(GroupSaleProduct.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("productId", p.getId());
//            非超级拼购
            criteria.andEqualTo("superType", 0);
//            条件查询,id,非超级拼团查询唯一数据
            List<GroupSaleProduct> groupSaleProducts = groupSaleProductMapper.selectByCondition(condition);
            if (groupSaleProducts == null || groupSaleProducts.size() != 1) {
//                TODO 拼团记录异常
                productInfoDTO.setGroupPeople(0);
                productInfoDTO.setTotalPeople(0);
            } else {
//                赋值拼团人数和总人数
                productInfoDTO.setGroupPeople(groupSaleProducts.get(0).getGroupPeople());
                productInfoDTO.setTotalPeople(groupSaleProducts.get(0).getTotalPeople());
                Integer productGroupTotalPeople = groupInfoMapper.sumGroupSaleNumberByProductId(p.getId());
                productInfoDTO.setProductGroupTotalPeople(productGroupTotalPeople);
            }
            productInfoDTOS.add(productInfoDTO);
        }
       IPage pageInfo = newIPage(productInfoDTOS);
        return pageInfo;
    }


    /**
     * @return
     * @Author liyuan
     * @Description 1-0-1-0-1	超级拼购
     */
    @Override
    public IPage listSuperGroupSaleList(Integer pageNum, Integer pageSize) {
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.PRODUCT_SUPER_GROUP_SALE_DEFAULT_PAGE_SIZE;
        }
//        查询所有超级拼团id
        List<Integer> groupSaleProductIds = productInfoMapper.listGetAllGroupSaleIds(1);
//        groupSaleProductIds去重,除null
        groupSaleProductIds = groupSaleProductIds.stream().distinct().collect(Collectors.toList());
        groupSaleProductIds = groupSaleProductIds.stream().filter(i -> i != null).collect(Collectors.toList());
        if (groupSaleProductIds.size() == 0) {
//            集合为空,返回空集合
            return newIPage(Arrays.asList());
        }
//        启动分页
        PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> productInfos = productInfoMapper.listGetProductsByIds(groupSaleProductIds, "", "");
        //        构建前端交互集合
        ArrayList<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        for (ProductInfo p : productInfos) {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(p, productInfoDTO);
            Condition condition = new Condition(GroupSaleProduct.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("productId", p.getId());
//            超级拼购
            criteria.andEqualTo("superType", 1);
//            条件查询,id,超级拼团查询唯一数据
            List<GroupSaleProduct> groupSaleProducts = groupSaleProductMapper.selectByCondition(condition);
            if (groupSaleProducts == null || groupSaleProducts.size() != 1) {
//                TODO 超级拼团记录异常
                productInfoDTO.setGroupPeople(0);
                productInfoDTO.setTotalPeople(0);
            } else {
//                赋值拼团人数和总人数
                productInfoDTO.setGroupPeople(groupSaleProducts.get(0).getGroupPeople());
                productInfoDTO.setTotalPeople(groupSaleProducts.get(0).getTotalPeople());
                Integer productGroupTotalPeople = groupInfoMapper.sumGroupSaleNumberByProductId(p.getId());
                productInfoDTO.setProductGroupTotalPeople(productGroupTotalPeople);
            }
            productInfoDTOS.add(productInfoDTO);
        }
       IPage pageInfo = newIPage(productInfoDTOS);
        return pageInfo;
    }


    /**
     * @param productId 商品id
     * @return
     * @Author liyuan
     * @Description 1-0-1-1-0 俱乐部拼购-商品详情
     */
    @Override
    public ProductInfoDTO getProductDetailByid(Integer productId) {
        ProductInfo productInfo = findById(productId);
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        BeanUtils.copyProperties(productInfo, productInfoDTO);
//        默认不拼团
        productInfoDTO.setGroupType(0);
//        拼团价 拼团人数
        List<GroupSaleProduct> groupSaleProducts = groupSaleProductMapper.getProductGroupSalePriceAndPeopleNumber(productId);
        if (groupSaleProducts.size() != 0) {
//                赋值拼团价和拼团人数
            productInfoDTO.setGroupSaleProductPrice(groupSaleProducts.get(0).getPrice());
            productInfoDTO.setGroupPeople(groupSaleProducts.get(0).getGroupPeople());
            productInfoDTO.setGroupType(1);
//            根据groupSaleId查询GroupSale  唯一性
            GroupSale groupSale = groupSaleMapper.selectByPrimaryKey(groupSaleProducts.get(0).getGroupSaleId());
            long toTime = (groupSale.getEndTime().getTime() - System.currentTimeMillis()) / 1000;
//            赋值距离结束时间(s)
            productInfoDTO.setEndTimeToSeconds(toTime);
            return productInfoDTO;
        }
        productInfoDTO.setFlashType(0);
        List<FlashSaleProductDTO> flashSaleProducts = flashSaleProductMapper.getDetailByProductId(productId);
        if (flashSaleProducts.size() != 0) {
//                赋值秒杀价和秒杀时间
            productInfoDTO.setFlashType(1);
            productInfoDTO.setFlashSaleProductPrice(flashSaleProducts.get(0).getPrice());
            long toTime = (flashSaleProducts.get(0).getEndTime().getTime() - System.currentTimeMillis()) / 1000;
//            赋值距离结束时间(s)
            productInfoDTO.setEndTimeToSeconds(toTime);
        }
///     注:剥离商品详情页评论(下面代码默认返回前两个评论信息)->接口-------------------------------------------------------------------------------------
        /*Condition condition = new Condition(ProductEvaluate.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("productId", productId);
        condition.orderBy("addTime");
        List<ProductEvaluate> productEvaluates = productEvaluateMapper.selectByCondition(condition);
        //好评率默认为-
        productInfoDTO.setPositiveRating("-");
        if(productEvaluates.size() > 0){
            //有评论
            List<ProductEvaluate> collect = productEvaluates.stream().filter(i -> i.getStar() == 5).collect(Collectors.toList());
            //        评论总人数
            int sizeAll = productEvaluates.size();
            //        五星评论人数
            int size = collect.size();
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float) size / (float) sizeAll * 100);
            productInfoDTO.setPositiveRating(result + "%");
            List<Integer> productEvaluateIds = new ArrayList<>();
            productEvaluateIds.add(productEvaluates.get(0).getId());
//            productEvaluates集合的长度已经大于0,如果集合只有1个元素的情况,可能出现NPE
            productEvaluateIds.add(Optional.ofNullable(productEvaluates.get(1)).get().getId());
            List<ProductEvaluateDTO>  productEvaluateDTOList=productEvaluateMapper.commonSelectByIds(productEvaluateIds);
            productInfoDTO.setProductEvaluateDTOList(productEvaluateDTOList);
        }*/
///     注------------------------------------------------------------------------------------------------------------------------------------

///     注:剥离商品详情页俱乐部拼团(下面代码默认返回前两个俱乐部信息)->接口------------------------------------------------------------------------------
//        查询所有 正在拼团的俱乐部(只获取前两条数据)
/*        PageHelper.startPage(1,2);
        List<GroupInfoDTO> groupInfoDTOS= groupInfoMapper.listGetClubGroupByproductId(productId);
        productInfoDTO.setGroupInfoDTOS(groupInfoDTOS);*/
///     注------------------------------------------------------------------------------------------------------------------------------------
        return productInfoDTO;
    }

    /**
     * @param columnName  分类名称
     * @param productName 产品名称
     * @param type        1:上架 0:下架
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/23 13:49
     */
    @Override
    public IPage findManagerList(Integer pageNum, Integer pageSize, Integer columnName, String productName, Integer type) {
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap();
        if (columnName != null) {
            map.put("columnName", columnName);
        }
        if (StringUtils.isNotEmpty(productName)) {
            map.put("productName", productName);
        }
        if (type != null) {
            map.put("type", type);
        }
        List<ProductInfoDTO> managerList = productInfoMapper.findManagerList(map);
        for (ProductInfoDTO productInfoDTO : managerList) {
            //产品标签
            List<String> stringList = JSONArray.parseArray(productInfoDTO.getProductTag(), String.class);
            productInfoDTO.setListProductTag(stringList);
            //产品分类名称
            if (productInfoDTO.getProductColumId() != null) {
                productInfoDTO.setProductColumnName(productColumnMapper.selectByPrimaryKey(productInfoDTO.getProductColumId()).getColumnName());
            }
        }
       IPage<ProductInfoDTO> pageInfo = newIPage<>(managerList);
        return pageInfo;
    }

    /**
     * @Description 根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/23 13:50
     */
    @Override
    public ProductInfoDTO findManagerById(Integer id) {
        ProductInfoDTO productInfoDTO = productInfoMapper.findManagerById(id);
        return productInfoDTO;
    }

    /**
     * @param
     * @return
     * @Author ws
     * @Description 分页查询积分商品列表
     */
    @Override
    public IPage getScoreProductList(Integer pageNum, Integer pageSize) {

        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        //创建返回对象
        List<ScoreProductVo> scoreProductsVoList = new ArrayList<>();

        Condition condition = new Condition(ProductInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        //商品状态: 上架1 下架0
        criteria.andEqualTo("type", 1);
        //积分商品 积分售价不可为空
        criteria.andIsNotNull("productScore");
        //积分商品不知排序规则 暂时按照销量、排序值倒序
        condition.orderBy("saleNum").desc();
        condition.orderBy("orderVal").desc();
        List<ProductInfo> productInfoList = productInfoMapper.selectByCondition(condition);

        productInfoList.forEach(productInfo -> {
            System.out.println("productinfo-----" + productInfo);
            ScoreProductVo scoreProductVo = new ScoreProductVo();
            BeanUtils.copyProperties(productInfo, scoreProductVo);
            scoreProductVo.setProductId(productInfo.getId());
            scoreProductsVoList.add(scoreProductVo);
        });
       IPage pageInfo = newIPage(scoreProductsVoList);

        return pageInfo;
    }

    /**
     * @param
     * @return
     * @Author ws
     * @Description 查询单个积分商品详情
     */
    @Override
    public ScoreProductInfoVo getScoreProductInfo(Integer productId) {
        //创建返回对象
        ScoreProductInfoVo scoreProductInfoVo = new ScoreProductInfoVo();
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);

        BeanUtils.copyProperties(productInfo, scoreProductInfoVo);
        //截取轮播图
        scoreProductInfoVo.setProductImgArray(productInfo.getProductImg().split(","));

        return scoreProductInfoVo;
    }


    /**
     * @param
     * @return
     * @description 查询秒杀活动以及正在进行秒杀的商品列表
     **/
    @Override
    public Map getNowFlashSaleList(Integer pageNum, Integer pageSize) {

        Map map = new HashMap();

        List<FlashSaleProductVo> flashSaleProductVoList = new ArrayList<>();
        //获取正在进行的秒杀活动
        FlashSale flashSale = flashSaleMapper.getCurrentFlashSale();
        if (flashSale != null) {
            //查询参与此秒杀活动的所有产品
            PageHelper.startPage(pageNum, pageSize);
            List<ProductInfoDTO> productInfoDTOList = productInfoMapper.getAllFlashSaleProduct(flashSale.getId());
            productInfoDTOList.forEach(productInfoDTO -> {
                FlashSaleProductVo flashSaleProductVo = new FlashSaleProductVo();
                BeanUtils.copyProperties(productInfoDTO, flashSaleProductVo);
                flashSaleProductVo.setProductId(productInfoDTO.getId());
                //获取秒杀商品的库存
                flashSaleProductVo.setStock(flashSaleProductSkuMapper.getFlashSaleProduckStock(productInfoDTO.getId()));
                flashSaleProductVoList.add(flashSaleProductVo);
            });
           IPage pageInfo = newIPage(flashSaleProductVoList);

            map.put("pageInfo", pageInfo);
            map.put("flashSale", flashSale);
        }
        return map;
    }

    /**
     * @param
     * @return
     * @description 查询秒杀活动或下期秒杀的商品列表
     **/
    @Override
    public Map getNextFlashSaleList(Integer pageNum, Integer pageSize) {

        Map map = new HashMap();

        List<FlashSaleProductVo> flashSaleProductVoList = new ArrayList<>();
        //获取正在抢购的秒杀活动
        FlashSale flashSale = flashSaleMapper.getNextFlashSale();
        if (flashSale != null) {
            //查询参与下期活动的所有产品
            PageHelper.startPage(pageNum, pageSize);
            List<ProductInfoDTO> productInfoDTOList = productInfoMapper.getAllFlashSaleProduct(flashSale.getId());
            productInfoDTOList.forEach(productInfoDTO -> {
                FlashSaleProductVo flashSaleProductVo = new FlashSaleProductVo();
                BeanUtils.copyProperties(productInfoDTO, flashSaleProductVo);
                flashSaleProductVo.setProductId(productInfoDTO.getId());
                //获取下期秒杀商品的库存
                flashSaleProductVo.setStock(flashSaleProductSkuMapper.getFlashSaleProduckStock(productInfoDTO.getId()));
                flashSaleProductVoList.add(flashSaleProductVo);
            });
           IPage pageInfo = newIPage(flashSaleProductVoList);

            map.put("pageInfo", pageInfo);
            map.put("flashSale", flashSale);
        }
        return map;
    }

    /**
     * @param orderType
     * @return
     * @description 根据分类查询商品列表
     **/
    @Override
    public List<ColumnProductVo> getProductsByColumnId(Integer columnId, Integer orderType, Integer pageNum, Integer pageSize) {
            List<ColumnProductVo> productVoList = new ArrayList<>();
            //查询指定分类的所有商品
            Condition condition = new Condition(ProductInfo.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("productColumId", columnId);
            //商品状态 1上架 0下架
            criteria.andEqualTo("type", 1);
            //商品排序
            if (orderType == 0) {
                //0默认排序 1销量 2价格正序 3价格倒序  4新品
                condition.orderBy("orderVal").desc();
            } else if (orderType == 1) {
                condition.orderBy("saleNum").desc();
            } else if (orderType == 2) {
                condition.orderBy("productSalePrice").asc();
            } else if (orderType == 3) {
                condition.orderBy("productSalePrice").desc();
            } else if (orderType == 4) {
                condition.orderBy("addTime").desc();
            }

            criteria.andEqualTo("type", 1);
            //默认按照排序值排序
            condition.orderBy("orderVal").desc();
            //获取所有商品
            List<ProductInfo> productInfoList = findByCondition(condition);
            productInfoList.forEach(productInfo -> {
                ColumnProductVo columnProductVo = new ColumnProductVo();
                BeanUtils.copyProperties(productInfo, columnProductVo);
                columnProductVo.setProductId(productInfo.getId());
                //判断是否是拼团商品
                List<GroupSaleProduct> groupSaleProducts = groupSaleProductMapper.getProductGroupSalePriceAndPeopleNumber(productInfo.getId());
                if (groupSaleProducts.size() != 0) {
                    columnProductVo.setJudgeGroupSale(true);
                    //默认显示商品售价，商品拼购价需进入商品详情才显示
                }
                //判断是否是秒杀商品
                List<FlashSaleProductDTO> flashSaleProducts = flashSaleProductMapper.getDetailByProductId(productInfo.getId());
                if (flashSaleProducts.size() != 0) {
                    columnProductVo.setJudgeFlashSale(true);
                    //将秒杀价赋值给商品售价
                    columnProductVo.setProductSalePrice(flashSaleProducts.get(0).getPrice());
                }
                productVoList.add(columnProductVo);
            });

            return productVoList;
        }



        /**
         * @param keywords
         * @return
         * @description 根据关键字模糊匹配商品
         **/
        @Override
        public IPage getProductsbySearch (String keywords, Integer pageNum, Integer pageSize){

            List<ColumnProductVo> columnProductVoList = new ArrayList<>();
            //启动分页
            PageHelper.startPage(pageNum, pageSize);
            //查询出改关键字对应的所有商品 默认排序值排序
            List<ProductInfo> productInfoList = productInfoMapper.getProductsByKeywords(keywords);
            productInfoList.forEach(productInfo -> {
                ColumnProductVo columnProductVo = new ColumnProductVo();
                BeanUtils.copyProperties(productInfo, columnProductVo);
                columnProductVo.setProductId(productInfo.getId());
                //判断是否是拼团商品
                List<GroupSaleProduct> groupSaleProducts = groupSaleProductMapper.getProductGroupSalePriceAndPeopleNumber(productInfo.getId());
                if (groupSaleProducts.size() != 0) {
                    columnProductVo.setJudgeGroupSale(true);
                    //默认显示商品售价，商品拼购价需进入商品详情才显示
                }
                //判断是否是秒杀商品
                List<FlashSaleProductDTO> flashSaleProducts = flashSaleProductMapper.getDetailByProductId(productInfo.getId());
                if (flashSaleProducts.size() != 0) {
                    columnProductVo.setJudgeFlashSale(true);
                    //将秒杀价赋值给商品售价
                    columnProductVo.setProductSalePrice(flashSaleProducts.get(0).getPrice());
                }
                columnProductVoList.add(columnProductVo);
            });
           IPage pageInfo = newIPage(columnProductVoList);
            return pageInfo;
        }

        @Override
        public Integer findCountByColumnId (Integer columnId){
            return productInfoMapper.findCountByColumnId(columnId);
        }

        @Override
        public void updateByOrderVal (String ids, String orderVal){
            List<Integer> idList = StringUtils.stringToIntegerList(ids);
            List<Integer> orderValList = StringUtils.stringToIntegerList(orderVal);
            for (int i = 0; i < idList.size(); i++) {
                productInfoMapper.updateByVal(idList.get(i), orderValList.get(i));
            }
        }

        @Override
        public void updateType (String ids, Integer type){
            List<Integer> integers = StringUtils.stringToIntegerList(ids);
            for (Integer s : integers) {
                productInfoMapper.updateType(s, type);
            }
        }


}
