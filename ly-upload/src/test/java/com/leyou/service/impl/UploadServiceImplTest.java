package com.leyou.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UploadServiceImplTest {
    @Autowired
    private DefaultFastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;


    @Test
    public void testUploadImage() throws FileNotFoundException {
        File file=new File("C:\\Users\\29607\\Desktop\\2.jpeg");
        StorePath storePath = this.storageClient.uploadFile(new FileInputStream(file), file.length(), "jpeg", null);
        //带分组路径
        String fullPath = storePath.getFullPath();
        System.out.println("fullPath = " + fullPath);
        //不带分组路径
        String path = storePath.getPath();
        System.out.println("path = " + path);
    }

    @Test
    public void testUploadThumbImage() throws FileNotFoundException {
        File file=new File("C:\\Users\\29607\\Desktop\\2.jpeg");
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file), file.length(), "jpeg", null);
        //带分组路径
        String fullPath = storePath.getFullPath();
        System.out.println("fullPath = " + fullPath);
        //不带分组路径
        String path = storePath.getPath();
        System.out.println("path = " + path);
        //略缩图路径
        String thumbImagePath = thumbImageConfig.getThumbImagePath(path);
        System.out.println(thumbImagePath);
    }


}