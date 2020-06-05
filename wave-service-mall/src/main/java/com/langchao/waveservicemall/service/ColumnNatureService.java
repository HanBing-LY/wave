package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.ColumnNature;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author
 * @Title: ColumnNatureService
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface ColumnNatureService extends IService<ColumnNature> {

    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/20 18:16
     */
   IPage findManagerList(Integer id, Integer page, Integer size);

    /**
     * @Description 后台根据产品分类id查询数据
     * @Author Renjinliang
     * @date 2020/3/24 19:54
     */
    @GetMapping("findByColumnId")
    List<ColumnNatureDTO> findByColumnId(Integer productColumId, String columnNatureId, String natureValueId);



    Integer findCountByColumnId(Integer columnId);
}
