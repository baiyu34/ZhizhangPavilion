package com.baiyu.zzg.controller;

import com.baiyu.zzg.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadcontroller {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\lo'v'ou\\Desktop\\tx\\"+ originalFilename));
        return Result.success("url访问地址...");
    }
}
