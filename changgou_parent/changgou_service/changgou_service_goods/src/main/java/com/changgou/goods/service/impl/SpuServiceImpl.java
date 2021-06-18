package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.IdWorker;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.entity.Goods;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SkuMapper skuMapper;



    //添加spu  和 sku
    @Override
    public void add(Goods goods) {

        //添加spu
        Spu spu = goods.getSpu();
        long spuId = idWorker.nextId();
        spu.setId(spuId);
        spu.setIsDelete("0");
        spu.setIsMarketable("0");
        spu.setStatus("0");
        spuMapper.insertSelective(spu);



        //添加sku
        saveSkuList(goods);

    }


    //根据id查询数据
    @Override
    public Goods findGoodsById(String id) {

        Spu spu = spuMapper.selectByPrimaryKey(Long.parseLong(id));

        //根据spu  查询   sku列表
        Example example = new Example(Sku.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("spuId",spu.getId());

        List<Sku> skuList = skuMapper.selectByExample(example);

        //将数据存储到  goods
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);

        return goods;
    }


    //修改操作
    @Override
    public void update(Goods goods) {

        //取出spu部分
        Spu spu = goods.getSpu();
        spuMapper.updateByPrimaryKey(spu);
        //删除原sku列表
        Example example=new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId",spu.getId());
        skuMapper.deleteByExample(example);

        saveSkuList(goods);//保存sku列表

    }


    //添加sku
    private void saveSkuList(Goods goods) {

        //获取spu
        Spu spu = goods.getSpu();

        //获取sku列表
        List<Sku> skuList = goods.getSkuList();

        //当前日期
        Date date = new Date();
        //获取品牌对象
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());

        //获取分类对象
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());

        //判断
        if(skuList != null){

            //遍历添加   sku列表的数据
            for (int i = 0; i < skuList.size(); i++) {

                Sku sku = skuList.get(i);

                //设置数据
                //设置sku主键ID
                sku.setId(idWorker.nextId());
                //设置sku规格
                if (sku.getSpec() == null || "".equals(sku.getSpec())) {
                    sku.setSpec("{}");
                }


                //设置sku名称(spu商品名称 + spu规格)
                String name = spu.getName();
                //将规格json字符串转换成Map
                //spec:  {"电视音响效果":"立体声","电视屏幕尺寸":"20英寸","尺码":"165"}
                Map specMap = JSON.parseObject(sku.getSpec(), Map.class);

                Set<String> keys = specMap.keySet();
                //遍历specMap
                for(String key:keys){

                    String value = (String)specMap.get(key);

                    name+=value;
                }

                sku.setName(name);//名称

                sku.setSpuId(spu.getId());//设置spu的ID
                sku.setCreateTime(date);//创建日期
                sku.setUpdateTime(date);//修改日期
                sku.setCategoryId(category.getId());//商品分类ID
                sku.setCategoryName(category.getName());//商品分类名称
                sku.setBrandName(brand.getName());//品牌名称
                skuMapper.insertSelective(sku);


            }

        }


    }
}
