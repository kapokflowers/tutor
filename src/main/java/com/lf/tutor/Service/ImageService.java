package com.lf.tutor.Service;

import com.lf.tutor.domain.Image;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface ImageService {
    //新增图片
    void insert(Image image);
    //获取图片列表
    List<Image> getImageList(Image image);
    //根据用户ID和图片类型获取图片
    Image getImage(Image image);
    //更新图片
    void update(Image image);
}
