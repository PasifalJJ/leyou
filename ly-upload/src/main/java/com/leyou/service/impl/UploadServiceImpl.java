package com.leyou.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.config.UploadProperties;
import com.leyou.service.UploadService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadProperties properties;

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
            if (!properties.getAllowTypes().contains(type)){
                log.error("上传文件失败，文件类型不匹配：{}",type);
                return null;
            }
            //2)、校验文件内容
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image == null){
                log.error("上传失败，文件内容不符合要求");
                return null;
            }
            //2、保存图片
            String extension= StringUtils.substringAfterLast(multipartFile.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),
                    multipartFile.getSize(),extension, null);
            //2.3、返回略缩图片地址
            String url=properties.getBaseUrl()+thumbImageConfig.getThumbImagePath(storePath.getFullPath());
            return url;
        } catch (IOException e) {
            log.error("文件上传失败",e);
            throw new LyException(ExceptionsEnums.FILE_UPLOAD_EXCEPTION);
        }
    }
}
