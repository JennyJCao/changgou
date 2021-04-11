package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.changgou.util.FastDFSClient.getTrackerUrl;

@RequestMapping("/upload")
@RestController
@CrossOrigin // 跨域
public class FileController {

    @PostMapping
    public String upload(@RequestParam("file")MultipartFile file) throws IOException, MyException {
        // getOriginalFilename()获取的是真正的文件名，而getName()方法获取的是上传文件的表单名：file
        // System.out.println("FileController: upload=======");
        // System.out.println("file.getName(): " + file.getName());
        // System.out.println("file.getOriginalFilename(): " + file.getOriginalFilename());
        FastDFSFile fastDFSFile = new FastDFSFile(file.getOriginalFilename(), file.getBytes(), StringUtils.getFilenameExtension(file.getOriginalFilename()));
        // System.out.println("file: " + fastDFSFile);
        String[] uploadResult = FastDFSClient.upload(fastDFSFile);
        // uploadResult 是个有两个元素的数组
        // 第一个元素是文件上传到的地址的组名称 group1
        // 第二个元素是文件的虚拟地址：M00/00/00/1.jpg
//        return "172.16.235.2:8080/" + uploadResult[0] + "/" + uploadResult[1];
        return getTrackerUrl() + "/" + uploadResult[0] + "/" + uploadResult[1];
        // 172.16.235.2:8080/group1/M00/00/00/rBDrAmBA3M2AectpAAO1WLzWet8743.jpg 3.jpg
    }

}
