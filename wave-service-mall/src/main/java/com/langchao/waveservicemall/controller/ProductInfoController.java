package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.ProductColumn;
import com.chemguan.entity.ProductInfo;
import com.chemguan.entity.ProductInfoDTO;
import com.chemguan.service.ProductColumnService;
import com.chemguan.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @Title: ProductInfoController
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@RestController
@RequestMapping("/product/info")
public class ProductInfoController  extends BaseController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductColumnService productColumnService;

    /**
     * 口:
     * 查询所有商品信息，可根据商品名称查询，根据提成比例排序（倒叙，正序），
     * 根据提成金额排序改成按折扣价排序（正序，倒叙）
     *
     * @param productName
     * @param orderBy
     * @param desc
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/findList")
    public JsonResult findList(
            @RequestParam(name = "costGoods", required = false) Integer costGoods,//是否是成本商品
            @RequestParam(name = "productName", required = false) String productName,
            @RequestParam(name = "orderBy", defaultValue = "1") Integer orderBy,// 1.售价排序2.按提成比例
            @RequestParam(name = "desc", defaultValue = "1") Integer desc,//1.倒叙 2.正序
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(ProductInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("type", 1);//上架产品
        if (costGoods != null) {
            criteria.andEqualTo("costGoods", costGoods);
        }
        if (productName != null) {
            criteria.andLike("productName", "%" + productName + "%");
        }
        if (orderBy == 1) {
            if (desc == 1) {
                condition.orderBy("productSalePrice").desc();
            } else {
                condition.orderBy("productSalePrice").asc();
            }
        } else {
            if (desc == 1) {
                condition.orderBy("percentage").desc();
            } else {
                condition.orderBy("percentage").asc();
            }
        }
        List<ProductInfo> list = productInfoService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return success(pageInfo);
    }


    /**
     * @return
     * @author ws
     * @description 积分商品列表
     **/
    @GetMapping("/getScoreProductList")
    public JsonResult getScoreProductList(@RequestParam(required = true) Integer pageNum,
                                      @RequestParam(required = true) Integer pageSize) {
        return success(productInfoService.getScoreProductList(pageNum, pageSize));
    }

    /**
     * @param productId 积分商品id
     * @return
     * @author ws
     * @description 积分商品详情
     **/
    @GetMapping("/getScoreProductInfo")
    public JsonResult getScoreProductInfo(@RequestParam(required = true) Integer productId) {
        return success(productInfoService.getScoreProductInfo(productId));
    }


    /**
     * @Author zzz
     * @Description //TODO 4-0-7 负责人权益-商品提成比例
     * @Date 15:01 2020/3/11
     * @param isCostGoods 是否成本价商品; 1：是; 2：不是
     * @param productName 商品名称
     * @param orderByType 排序依据 1提成金额 2提成比例
     * @param descType 排序顺序 1倒序 2正序
     * @param page 页码
     * @param size
     * @return com.chemguan.business.results.JsonResult
     **/
    /*@GetMapping("/getList")
    public JsonResult getList(
            @RequestParam (name="isCostGoods",defaultValue = "") Integer isCostGoods,//是否是成本商品
            @RequestParam (name="productName",defaultValue = "") String productName,
            @RequestParam (name="orderByType",defaultValue = "1") Integer orderByType,// 1.提成价格排序2.按提成比例
            @RequestParam (name="descType",defaultValue = "1") Integer descType,//1.倒序2.正序
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size) {

        Map map = new HashMap();
        if(isCostGoods !=null){
            map.put("isCostGoods", isCostGoods);
        }
        if(StringUtils.isNotBlank(productName)) {
            map.put("productName", productName);
        }
        map.put("orderByType", orderByType);
        map.put("descType", descType);
        PageHelper.startPage(page, size);
        List<ProductInfo> list = ProductInfoService.getList(map);
        PageInfo pageInfo = new PageInfo(list);
        return success(pageInfo);
    }*/


    /**
     * 收藏的商品
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/collectList")
    public JsonResult collectList(@RequestParam(name = "userId") Integer userId,
                              @RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<ProductInfo> list = productInfoService.getCollectListByUserId(userId);
        PageInfo pageInfo = new PageInfo(list);
        return success(pageInfo);
    }


    /**
     * 1-0-0 商城首页热销商品
     *
     * @return
     */
    @GetMapping("/hotsalelist")
    public JsonResult listHotSaleList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(productInfoService.listHotSaleList(pageNum, pageSize));
    }

    /**
     * 1-0-0 商城首页最新上架商品
     *
     * @return
     */
    @GetMapping("/newpushlist")
    public JsonResult listNewPushList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(productInfoService.listNewPushList(pageNum, pageSize));
    }

    /**
     * 1-0-1-0-0 俱乐部拼团
     *
     * @param orderType 0:默认，按照商品排序值排序
     *                  1:按商品销量由高到低排序
     *                  2:按照商品销量由低到高排序
     *                  3:按照价格由高到低排序
     *                  4:按照价格由低到高排序
     * @return
     */
    @GetMapping("/groupsalelist")
    public JsonResult listGroupSaleList(@RequestParam(name = "orderType") Integer orderType, @RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(productInfoService.listGroupSaleList(orderType, pageNum, pageSize));
    }

    /**
     * 1-0-1-0-1	超级拼购
     *
     * @return
     */
    @GetMapping("/supergroupsalelist")
    public JsonResult listSuperGroupSaleList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(productInfoService.listSuperGroupSaleList(pageNum, pageSize));
    }

    /**
     * 1-0-1-1-0 俱乐部拼购-商品详情
     *
     * @param id 商品id
     * @return
     */
    @GetMapping("/productdetail")
    public JsonResult getProductDetailByid(@RequestParam(name = "id", required = true) Integer id) {
        return success(productInfoService.getProductDetailByid(id));
    }

    /**
     * @param productId
     * @return
     * @Author liyuan
     * 根据 产品id查询 产品单表信息
     */
    @GetMapping("/getById")
    public JsonResult getById(@RequestParam(name = "productId", required = true) Integer productId) {
        return success(productInfoService.findById(productId));
    }

    /**
     * @param
     * @return com.chemguan.business.results.JsonResult
     * @description 获取正在进行的秒杀活动时间、商品列表
     **/
    @GetMapping("/getNowFlashSale")
    public JsonResult getNowFlashSale(@RequestParam(required = true) Integer pageNum,
                                  @RequestParam(required = true) Integer pageSize) {

        Map map = productInfoService.getNowFlashSaleList(pageNum, pageSize);
        return map.containsKey("flashSale") ? success(map) : JsonResultGenerator.genFailJsonResult("暂无秒杀活动");
    }

    /**
     * @param
     * @return com.chemguan.business.results.JsonResult
     * @description 获取即将开启的秒杀活动时间、商品列表
     **/
    @GetMapping("/getNextFlashSale")
    public JsonResult getNextFlashSale(@RequestParam(required = true) Integer pageNum,
                                   @RequestParam(required = true) Integer pageSize) {
        Map map = productInfoService.getNextFlashSaleList(pageNum, pageSize);
        return map.containsKey("flashSale") ? success(map) : JsonResultGenerator.genFailJsonResult("暂无下期秒杀活动");
    }
    /**
     * @description 根据分类id获取商品列表
     * @param orderType 0默认 1销量 2价格正序 3价格倒序 4新品
     * @return com.chemguan.business.results.JsonResult
     **/
    @GetMapping("/getProductListByColumnId")
    public JsonResult getProductListByColumnId(@RequestParam(required = true) Integer columnId,
                                           @RequestParam(required = true,defaultValue = "0") Integer orderType,
                                            @RequestParam(required = true) Integer pageNum,
                                           @RequestParam(required = true) Integer pageSize){
        return success(productInfoService.getProductsByColumnId(columnId,orderType, pageNum, pageSize));
    }



    /**
     * @Description 后台添加
     * @Author Renjinliang
     * @date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody ProductInfo ProductInfo) {
        List<ProductColumn> listChild = productColumnService.findbyparent(ProductInfo.getProductColumId());
        if (listChild.size() > 0) {
            return JsonResultGenerator.genFailJsonResult("该分类下存在子分类，请重新选择产品分类");
        } else {
            ProductInfo.setAddTime(new Date());
            productInfoService.save(ProductInfo);
            return success();
        }
    }

    /**
     * 后台批量删除
     *
     * @param checkIds
     * @return
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if (StringUtils.isEmpty(checkIds)) {
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try {
            productInfoService.deleteByIds(checkIds);
        } catch (Exception e) {
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
     * @Description 后台修改
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody ProductInfo ProductInfo) {
        productInfoService.update(ProductInfo);
        return success();
    }

    /**
     * @Description 后台详情
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        ProductInfoDTO productInfoDTO = productInfoService.findManagerById(id);
        String productImg = productInfoDTO.getProductImg();
        if (StringUtils.isNotEmpty(productImg)) {
            String[] split = productImg.split(",");
            productInfoDTO.setImgList(split);
        }
        return success(productInfoDTO);
    }

    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "columnName", defaultValue = "") Integer columnName,
            @RequestParam(name = "productName", defaultValue = "") String productName,
            @RequestParam(name = "type", defaultValue = "") Integer type) {
        return success(productInfoService.findManagerList(page, size, columnName, productName, type));
    }

    /**
     * @Description 后台根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/23 17:17
     */
    @GetMapping("findManagerById")
    public ProductInfo findManagerById(@RequestParam(name = "id", defaultValue = "") Integer id) {
        ProductInfo productInfo = productInfoService.findById(id);
        return productInfo;
    }

    /**
     * @Description 根据分类id查询产品
     * @Author Renjinliang
     * @date 2020/3/27 14:30
     */
    @GetMapping("findByProductColumnId")
    public JsonResult findByProductColumnId(@RequestParam(name = "id", defaultValue = "") Integer id) {
        List<ProductColumn> listChild = productColumnService.findbyparent(id);
        if (listChild.size() > 0) {
            return JsonResultGenerator.genFailJsonResult("该分类下存在自分类，请先删除自分类");
        } else {
            Condition condition = new Condition(ProductInfo.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("productColumId", id);
            List<ProductInfo> productInfoList = productInfoService.findByCondition(condition);
            return success(productInfoList);
        }
    }

    /**
     * @Description 修改排序值
     * @Author Renjinliang
     * @date 2020/3/27 18:04
     */
    @PostMapping("updateByOrderVal")
    public JsonResult updateByOrderVal(@RequestParam(name = "ids", defaultValue = "") String ids,
                                   @RequestParam(name = "orderVal", defaultValue = "") String orderVal) {
        if (StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(orderVal)) {
            productInfoService.updateByOrderVal(ids, orderVal);
        }
        return success();
    }

    /**
    *@Description 修改上架状态
    *@Author Renjinliang
    *@date 2020/3/27 20:54
    */
    @PostMapping("updateType")
    public JsonResult updateType(@RequestParam(name = "ids", defaultValue = "") String ids,
                             @RequestParam(name = "type", defaultValue = "") Integer type){
        if(StringUtils.isNotEmpty(ids)){
            productInfoService.updateType(ids, type);
        }
        return success();
    }

}
