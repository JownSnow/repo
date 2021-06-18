package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.entity.Goods;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    //注入mapper
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public String findTest() {
        return "helloWorld";
    }

    @Override
    public List<Brand> findAll() {

        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }


    //条件查询
    @Override
    public List<Brand> findList(Brand brand) {

        //获取设置查询条件的对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if(brand != null){

            //判断   name   letter  是否为空
            if (brand.getName() != null && brand.getName() != ""){

                //添加name   模糊查询条件
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            //判断  letter
            if(brand.getLetter() != null && brand.getLetter() != ""){

                criteria.andEqualTo("letter",brand.getLetter());
            }
        }

        List<Brand> brandList = brandMapper.selectByExample(example);   //null    查询全部

        return brandList;
    }


    //条件查询
    @Override
    public List<Brand> findListByMap(Map<String, Object> searchMap) {

        //获取设置查询条件的对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if(searchMap != null){

            //判断   name   letter  是否为空
            if (searchMap.get("name") != null && searchMap.get("name") != ""){

                //添加name   模糊查询条件
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            //判断  letter
            if(searchMap.get("letter") != null && searchMap.get("letter") != ""){

                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }

        List<Brand> brandList = brandMapper.selectByExample(example);   //null    查询全部

        return brandList;
    }


    //条件分页查询
    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {


        PageHelper.startPage(page,size);

        //获取设置查询条件的对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if(searchMap != null){

            //判断   name   letter  是否为空
            if (searchMap.get("name") != null && searchMap.get("name") != ""){

                //添加name   模糊查询条件
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            //判断  letter
            if(searchMap.get("letter") != null && searchMap.get("letter") != ""){

                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }

       Page<Brand> pageBean  = ( Page<Brand>)brandMapper.selectByExample(example);

        return pageBean;
    }

    @Override
    public List<Brand> findListByCategoryName(String name) {
        return brandMapper.findListByCategoryName(name);
    }





}
