package com.liyuan.wave.po.ucenter;


import lombok.Data;

import java.util.List;


@Data
public class CategoryNode extends Category {
    List<CategoryNode> children;
}
