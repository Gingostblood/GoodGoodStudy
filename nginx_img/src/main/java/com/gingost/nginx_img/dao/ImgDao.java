package com.gingost.nginx_img.dao;

import com.gingost.nginx_img.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:lezzy
 * @Date:2020/3/30 13:57
 */
public interface ImgDao extends JpaRepository<Image, Integer> {
}
