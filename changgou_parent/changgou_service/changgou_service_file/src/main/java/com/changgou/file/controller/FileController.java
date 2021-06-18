package com.changgou.file.controller;


import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.pojo.FastDFSFile;
import com.changgou.file.util.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping("/upload.do")
    public Result uploadFile(MultipartFile file){

        try {

            //判断文件是否存在
            if (file == null){
                throw new RuntimeException("文件不存在");
            }

            String originalFilename = file.getOriginalFilename();

//            System.out.println("originalFilename: " + originalFilename);

            if (StringUtils.isEmpty(originalFilename)){
                throw new RuntimeException("文件不存在");
            }
            


            //获取文件的扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //获取文件内容
            byte[] content = file.getBytes();

            //创建文件上传的封装实体类
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename,content,extName);

            //文件上传
            //基于工具类进行文件上传,并接受返回参数  String[]
            String[] uploadResult = FastDFSClient.upload(fastDFSFile);

//            for (String str : uploadResult) {
//                System.out.println(str);
//            }

            //封装返回结果
            String url = FastDFSClient.getTrackerUrl()+uploadResult[0]+"/"+uploadResult[1];

            return new Result(true, StatusCode.OK,"上传成功",url);

        }catch (Exception e){

            return new Result(false, StatusCode.ERROR,"上传失败: " +e.getMessage());
        }

    }

}
