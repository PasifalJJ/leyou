package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableZuulProxy
public class LyApiGeteway {
    public static void main(String[] args) {
        SpringApplication.run(LyApiGeteway.class,args);
    }
}
