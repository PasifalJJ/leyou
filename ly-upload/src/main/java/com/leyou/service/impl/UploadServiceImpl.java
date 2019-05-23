package com.leyou.service.impl;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.leyou.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    //定义支持文件的类型
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg");

    @Autowired
    private DefaultFastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        try {
            //1、图片信息校验
            //1)、校验文件类型
            String type=multipartFile.getContentType();
            if (!suffixes.contains(type)){
                log.info("上传文件失败，文件类型不匹配：{}",type);
                return null;
            }
            //2)、校验文件内容
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image == null){
                log.info("上传失败，文件内容不符合要求");
                return null;
            }
            //2、保存图片
            InputStream is = multipartFile.getInputStream();
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),
                    multipartFile.getSize(), multipartFile.getContentType().split("/")[1], null);
            //2.3、返回略缩图片地址
            String url="http://image.leyou.com/"+thumbImageConfig.getThumbImagePath(storePath.getFullPath());
            return url;
        } catch (IOException e) {
            return null;
        }
    }
}
