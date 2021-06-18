package com.changgou.goods.service;

import com.changgou.goods.entity.Goods;
import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    //测试
    public String findTest();


    //查询全部
    public List<Brand> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Brand findById(Integer id);

    /***
     * 新增品牌
     * @param brand
     */
    public void add(Brand brand);

    /***
     * 修改品牌数据
     * @param brand
     */
    void update(Brand brand);

    /***
     * 删除品牌
     * @param id
     */
    void delete(Integer id);

    /***
     * 多条件搜索品牌方法
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /***
     * 多条件搜索品牌方法
     * @return
     */
    List<Brand> findListByMap(Map<String ,Object> searchMap);


    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Brand> findPage(Map<String, Object> searchMap, int page, int size);


    //根据分类名称查询品牌列表
    public List<Brand> findListByCategoryName(String name);



}
