package com.changgou.goods.controller;

import com.changgou.entity.IdWorker;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.entity.Goods;
import com.changgou.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    @Autowired
    private IdWorker idWorker;

    //根据分类名称查询规格列表
    @RequestMapping("/testId.do")   //基于 restful 访问格式的http请求
    public Result findMapByCategoryName(){
        return  new Result(true,200,"查询成功",idWorker.nextId());
    }



    //添加spu和sku商品列表
    @RequestMapping("/add.do")
    public Result add(@RequestBody Goods goods){

        try{

            spuService.add(goods);

            return  new Result(true, StatusCode.OK,"添加成功");
        }catch (Exception e){

            return  new Result(false,StatusCode.ERROR,"添加失败: " +e.getMessage());
        }

    }




    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Goods goods = spuService.findGoodsById(id);
        return new Result(true,StatusCode.OK,"查询成功",goods);
    }



    /***
     * 修改数据
     * @param goods
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody Goods goods,@PathVariable String id) {
        spuService.update(goods);
        return new Result(true, StatusCode.OK, "修改成功");
    }
}
