package com.lf.tutor.Service.impl;

import com.lf.tutor.Service.ImageService;
import com.lf.tutor.domain.Image;
import com.lf.tutor.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public void insert(Image image) {
        imageMapper.insert(image);
    }

    @Override
    public List<Image> getImageList(Image image) {
        return imageMapper.getImageList(image);
    }

    @Override
    public Image getImage(Image image) {
        return imageMapper.getImage(image);
    }

    @Override
    public void update(Image image) {
        imageMapper.update(image);
    }
}
