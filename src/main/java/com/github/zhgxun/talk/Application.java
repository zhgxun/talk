package com.github.zhgxun.talk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * ServletComponentScan 主动扫描所有拦截器
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.github.zhgxun.talk.common.filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
