package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.dao.ProductColumnRepository;
import com.chemguan.entity.ProductColumn;
import com.chemguan.entity.ProductColumnDto;
import com.chemguan.service.ColumnNatureService;
import com.chemguan.service.ProductColumnService;
import com.chemguan.service.ProductInfoService;
import com.chemguan.utils.ColumChildrenUtil;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductColumnController
 * @Deacription TODO
 * @Author zzz
 * @Date 2020/2/21 15:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/product/column")
public class ProductColumnController  extends BaseController {
    @Autowired
    private ProductColumnService productColumnService;

    @Autowired
    private ProductColumnRepository productColumnRepository;

    @Autowired
    private ColumnNatureService columnNatureService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * @Author ws
     * @Description 获取产品分类 0所有分类 其他columnId指定查询
     * @Param
     **/
    @GetMapping("/getProductColumnList")
    public JsonResult getProductColumnList(@RequestParam(defaultValue = "0") Integer columnId) {

        ColumChildrenUtil columChildrenUtil = new ColumChildrenUtil();
        //查询所有分类
        List<ProductColumn> listColum = findAllColumn();
        List<ProductColumnDto> listColumDto = new ArrayList();
        for (ProductColumn productColumn : listColum) {
            System.out.println(productColumn);
            ProductColumnDto productColumnDto = new ProductColumnDto();
            BeanUtils.copyProperties(productColumn, productColumnDto);
            listColumDto.add(productColumnDto);
        }
        //调用递归工具将数据转化成list嵌套
        List<ProductColumnDto> findchildren = columChildrenUtil.findchildren(columnId, listColumDto);
        return success(findchildren);
    }

    /**
     * @return com.chemguan.business.results.JsonResult
     * @Author zzz
     * @Description 获取所有的产品分类
     * @Date 15:29 2020/2/21
     * @Param []
     **/
    @GetMapping("/getAll")
    public JsonResult getAll() {
        List<ProductColumn> productColumnList = productColumnService.findAll();
        return success(productColumnList);
    }

    /**
     * @Description 查询所有二级分类
     * @Author Renjinliang
     * @date 2020/3/24 15:07
     */
    @GetMapping("managerProColumn")
    public List<ProductColumn> managerProColumn() {
        Condition condition = new Condition(ProductColumn.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("parentId");
        List<ProductColumn> columnList = productColumnService.findByCondition(condition);
        return columnList;
    }


    /**
     * @param productColumn 产品分类表
     * @Description 后台添加
     * @Return
     * @Author Renjinliang
     * @Date 2020/3/20 15:40
     */
    @PostMapping
    public JsonResult add(@RequestBody ProductColumn productColumn) {
        try {
            productColumnService.addProductColumn(productColumn);
        } catch (Exception e) {
            e.getMessage();
            return JsonResultGenerator.genFailJsonResult("添加失败");
        }
        return success();
    }

    /**
     * @Description 后台删除
     * @Author Renjinliang
     * @date 2020/3/20 15:58
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if (StringUtils.isEmpty(checkIds)) {
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try {
            productColumnService.deleteByIds(checkIds);
        } catch (Exception e) {
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }


    /**
     * @Description 后台修改
     * @Author Renjinliang
     * @date 2020/3/20 15:59
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody ProductColumn productColumn) {
        productColumnService.update(productColumn);
        return success();
    }

    /**
     * @Description 后台根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/20 16:01
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(productColumnService.findById(id));
    }

    @GetMapping
    public JsonResult list(@RequestParam(name = "page", defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "size", defaultValue = "10") Integer pageSize) {
        return success(productColumnService.findManagerList(pageNum, pageSize));
    }

    /**
     * @Description 查询全部产品分类
     * @Author Renjinliang
     * @date 2020/3/23 14:33
     */
    @GetMapping("findAllColumn")
    public List<ProductColumn> findAllColumn() {
        return productColumnService.findAll();
    }


    /**
     * 新增分类
     *
     * @param columnName
     * @param parentId
     * @return
     */
    @PostMapping("addnewcolum")
    public JsonResult addnewcolum(String columnName, Integer parentId) {
        if (StringUtils.isEmpty(columnName)) {
            return JsonResultGenerator.genFailJsonResult("分类名称不得为空");
        }
        if (parentId == null) {
            return JsonResultGenerator.genFailJsonResult("父分类不得为空");
        }
        int level = 0;
        ProductColumn productColumn = productColumnService.findById(parentId);
        if (productColumn != null) {
            level = productColumn.getColumnLevel() + 1;
        }
        ProductColumn column = new ProductColumn();
        column.setColumnLevel(level);
        column.setColumnName(columnName);
        column.setParentId(parentId);
        productColumnService.save(column);
        return success();
    }


    /**
     * 删除产品分类
     *
     * @param columnId
     * @return
     */
    @GetMapping("deletecolum")
    public JsonResult deletecolum(Integer columnId) {


        ProductColumn productColumn = productColumnService.findById(columnId);
        if (productColumn == null) {
            return JsonResultGenerator.genFailJsonResult("分类不存在");
        }
        List<ProductColumn> listChild = productColumnService.findbyparent(columnId);
        if (listChild.size() > 0) {
            return JsonResultGenerator.genFailJsonResult("该分类下存在自分类，请先删除自分类");
        }

        //判断该分类是否被分类属性关联
        Integer columNatureCount = columnNatureService.findCountByColumnId(columnId);
        if (columNatureCount > 0) {
            return JsonResultGenerator.genFailJsonResult("该分类已被属性关联，请删除关联属性");
        }

        //判断该分类是否被商品关联
        Integer columProductCount = productInfoService.findCountByColumnId(columnId);
        if (columProductCount > 0) {
            return JsonResultGenerator.genFailJsonResult("该分类已被商品关联，请删除关联商品");
        }
        productColumnService.deleteById(columnId);
        return success();
    }

    /**
     * @Description 根据id查询详情
     * @Author Renjinliang
     * @date 2020/3/28 18:06
     */
    @GetMapping("productColumnById")
    public ProductColumn productColumnById(Integer id) {
        return productColumnService.findById(id);
    }

}
