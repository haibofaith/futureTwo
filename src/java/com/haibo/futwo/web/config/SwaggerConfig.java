package com.haibo.futwo.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author:haibo.xiong
 * @date:2019/3/15
 * @description:
 */
@Component
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan("com.haibo.futwo.web.ctrl")
public class SwaggerConfig {
    @Bean
    public Docket createAPI() {
        return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).select().apis(RequestHandlerSelectors.any())
                //过滤生成链接
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact=new Contact("xionghaibo","","1489280205@qq.com");
        ApiInfo apiInfo = new ApiInfoBuilder().license("Version 1.0").title("Swagger集成").description("Swagger API 测试").contact(contact).version("1.0").build();
        return apiInfo;
    }
}
