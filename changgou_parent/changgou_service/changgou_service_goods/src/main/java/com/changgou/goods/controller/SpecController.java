package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    //根据分类名称查询规格列表
    @RequestMapping("/findMapByCategoryName.do/{name}")   //基于 restful 访问格式的http请求
    public Result findMapByCategoryName(@PathVariable String  name){
        List<Map> list = specService.findMapByCategoryName(name);
        return  new Result(true,200,"查询成功",list);
    }

}
