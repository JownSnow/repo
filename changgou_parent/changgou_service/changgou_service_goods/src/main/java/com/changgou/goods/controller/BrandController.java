package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    //测试
    @RequestMapping("/findTest.do")
    public String findTest(){

        System.out.println("访问了吗~");

        return brandService.findTest();
    }


    //查询全部
    @RequestMapping("/findAll.do")
    public Result findAll(){
        List<Brand> brandList = brandService.findAll();
        return  new Result(true,200,"查询成功",brandList);
    }


    //根据id查询
    @GetMapping
//   @RequestMapping("/findById.do/{id}")   //基于 restful 访问格式的http请求
    public Result findById(@PathVariable int id){
        Brand brand = brandService.findById(id);
        return  new Result(true,200,"查询成功",brand);
    }


    //添加brand
//    @RequestMapping("/insert.do")   //基于 restful 访问格式的http请求
    @PostMapping
    public Result add(@RequestBody Brand brand){   //将接受的json串   转成pojo类 , 封装到   brand中

      /*  try{

            brandService.add(brand);

            return  new Result(true, StatusCode.OK, "添加成功");

        }catch (Exception e){


            return  new Result(true, StatusCode.ERROR, "添加失败");
        }
*/
        int num = 10/0;

        brandService.add(brand);

        return  new Result(true, StatusCode.OK, "添加成功");

    }



    //修改
// @PutMapping
   @RequestMapping("/update.do/{id}")   //基于 restful 访问格式的http请求
    public Result update(@PathVariable int id,@RequestBody Brand brand){
        //设置id
        brand.setId(id);
        brandService.update(brand);
        return  new Result(true,200,"修改成功");
    }



    //根据id删除
//    @PutMapping
    @RequestMapping("/delete.do/{id}")   //基于 restful 访问格式的http请求
    public Result update(@PathVariable int id){
        brandService.delete(id);
        return  new Result(true,200,"删除成功");
    }


    //品牌列表条件查询
/*    @RequestMapping("/findList.do")   //基于 restful 访问格式的http请求
    public Result findList(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.findList(brand);
        return  new Result(true,200,"查询成功",list);
    }*/


    //品牌列表条件查询
    @RequestMapping("/findListByMap.do")   //基于 restful 访问格式的http请求
    public Result findListByMap(@RequestParam Map searchMap){
        List<Brand> list = brandService.findListByMap(searchMap);
        return  new Result(true,200,"查询成功",list);
    }



    //分页条件查询
    @RequestMapping("/findPage.do/{page}/{size}")   //基于 restful 访问格式的http请求
    public Result findPage(@RequestParam Map searchMap,@PathVariable int page,@PathVariable int size){
        Page pageBean = brandService.findPage(searchMap, page, size);
        return  new Result(true,200,"查询成功",pageBean.getResult());
    }



    //根据分类名称查询品牌列表
    @RequestMapping("/findListByCategoryName.do/{name}")   //基于 restful 访问格式的http请求
    public Result findListByCategoryName(@PathVariable String  name){
        List<Brand> list = brandService.findListByCategoryName(name);
        return  new Result(true,200,"查询成功",list);
    }

}
