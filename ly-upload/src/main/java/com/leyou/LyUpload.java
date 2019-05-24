package com.leyou;

import com.leyou.config.UploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(UploadProperties.class)
public class LyUpload {
    public static void main(String[] args) {
        SpringApplication.run(LyUpload.class,args);
    }
}
