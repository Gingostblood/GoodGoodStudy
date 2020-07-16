package com.gingost.nginx_img.controller;

import com.gingost.nginx_img.domain.Image;
import com.gingost.nginx_img.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author:lezzy
 * @Date:2020/3/30 10:16
 */

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class PageController {
    private ImageService imageService;

    @RequestMapping("img")
    public String goPage() {
        return "img";
    }

    @RequestMapping("upload")
    @ResponseBody
    public Image uoload(MultipartFile uploadFile) throws IOException {
        return imageService.uploadImg(uploadFile);
    }
}
