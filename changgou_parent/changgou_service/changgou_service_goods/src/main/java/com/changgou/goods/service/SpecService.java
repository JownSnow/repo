package com.changgou.goods.service;

import java.util.List;
import java.util.Map;

public interface SpecService {

    //根据分类名称查询规格列表
    public List<Map> findMapByCategoryName(String name);

}
