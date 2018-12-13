package com.github.zhgxun.talk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 接口文档
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket create() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.zhgxun.talk.controlle"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档描述
     *
     * @return 文档描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("服务端接口文档")
                // 联系人
                .contact(new Contact("zhgxun", "https://github.com/zhgxun/talk", "zhgxun1989@163.com"))
                .version("1.0.0")
                // 描述
                .description("服务端接口文档, 统一提供给小程序和Web服务使用。")
                .build();
    }
}
