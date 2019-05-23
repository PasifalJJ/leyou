package com.leyou.web;

import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@RestController
@RequestMapping("upload")
public class UploadController {
//    private static final Logger log= LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     * @param multipartFile
     * @return  返回图片上传的地址
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
       String url = uploadService.uploadImage(multipartFile);
       if (StringUtils.isBlank(url)){
          //url为空，上传失败
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       //返回200，并携带urk路径
        return ResponseEntity.ok(url);
    }









    public ResponseEntity<String> uploadImagebak(@RequestParam("file") MultipartFile multipartFile) {
        File file = new File("imgs");
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        InputStream is = null;
        BufferedOutputStream bos = null;
        try {
            is = multipartFile.getInputStream();
            bos = new BufferedOutputStream(new FileOutputStream(file + originalFilename));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LyException(ExceptionsEnums.FILE_UPLOAD_EXCEPTION);
        } finally {
            if (bos!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
                }
            }
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    throw new LyException(ExceptionsEnums.CANNOT_FIND_BRAND);
                }
            }
            bos=null;
            is=null;
        }
        return ResponseEntity.ok("上传成功");
    }
}
