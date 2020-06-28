package com.gingost.nginx_img.service;

import com.gingost.nginx_img.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author:lezzy
 * @Date:2020/3/30 13:59
 */
public interface ImageService {
    Image uploadImg(MultipartFile uploadFile) throws IOException;
}
