package com.example.jpa.service;

import com.example.jpa.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 文件上传
 */
@RestController
public class UploadFileTest {


    @PostMapping(value = "/file/uploadFile")
    public String doUploadFile(@RequestParam(value = "file")MultipartFile file , HttpServletRequest request , User user) throws Exception {
        //获取物理路径webapp所在的路径
        String pathFile = request.getSession().getServletContext().getRealPath( "" );
        String path = null;
        if(!file.isEmpty()) {
            //生成文件储存路径
            String fileName = UUID.randomUUID().toString().replace( "-","" ) + user.getId();
            //获取文件类型
            String fielType = file.getContentType();
            //获得文件后缀名称
            String imageName=fielType.substring(fielType.indexOf("/")+1);
            //文件夹名称
            path = "/file/" + fileName + imageName;
            //上传文件到path路径
            file.transferTo( new File( path ) );
        }
        return "ddd";
    }
}
