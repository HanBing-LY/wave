package com.liyuan.wave.po.ucenter.ext;


import com.liyuan.wave.po.ucenter.CategoryNode;
import com.liyuan.wave.po.ucenter.Menu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class MenuExt extends Menu {

    List<CategoryNode> children;
}
