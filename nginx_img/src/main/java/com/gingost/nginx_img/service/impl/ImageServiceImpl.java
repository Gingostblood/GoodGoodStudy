package com.gingost.nginx_img.service.impl;

import com.gingost.nginx_img.dao.ImgDao;
import com.gingost.nginx_img.domain.Image;
import com.gingost.nginx_img.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author:lezzy
 * @Date:2020/3/30 13:59
 */
@Service
public class ImageServiceImpl implements ImageService {
    private String localURL = "G:/exingImg/";
    private String HttpURL = "http://www.img.com/";
    private ImgDao imgDao;

    public ImageServiceImpl(ImgDao imgDao) {
        this.imgDao = imgDao;
    }

    @Override
    public Image uploadImg(MultipartFile uploadFile) throws IOException {

        //获取真实图片名字 xxx.jpg
        String fileName = uploadFile.getOriginalFilename();
        //转换成小写
        fileName = fileName.toLowerCase();
        //按日期实现分文件夹保存
        String datePath = new SimpleDateFormat("yyyy/MM/dd/").format((new Date()));
        String realPath = localURL + datePath;
        //这一步生成了G:/exingImg/2020/03/31/
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //转换成URL路径
        String uuid = UUID.randomUUID().toString();
        int beginIndex = fileName.lastIndexOf(".");
        String type = fileName.substring(beginIndex);
        //8e80d708-2511-4ede-b53a-6b7820b6ce86.png
        String uuidName = uuid + type;
        //G:/exingImg/2020/03/31/8e80d708-2511-4ede-b53a-6b7820b6ce86.png
        String uploadFilePath = realPath + uuidName;
        //实现“上传功能”
        uploadFile.transferTo(new File(uploadFilePath));
        //nginx实现“网络地址”
        //http://www.img.com/2020/03/31/8e80d708-2511-4ede-b53a-6b7820b6ce86.png
        String url = HttpURL + datePath + uuidName;
        //SpringDataJPA将网络地址存储到数据库中
        Image img = new Image();
        img.setTitle(url).setContent("lezzy");
        imgDao.save(img);
        return img;
    }
}
