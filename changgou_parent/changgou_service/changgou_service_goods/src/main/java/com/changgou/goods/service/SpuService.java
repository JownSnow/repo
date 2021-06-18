package com.changgou.goods.service;

import com.changgou.goods.entity.Goods;

public interface SpuService {



    /***
     * 新增   spu +  sku
     * @param goods
     */
    void add(Goods goods);


    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    public Goods findGoodsById(String id);


    /***
     * 修改数据
     * @param   goods
     */
    void update(Goods goods);


}
