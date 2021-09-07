package com.energizeglobal.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * A <i>SwaggerConfig</i>. This class has responsibility to configure the swagger for exposing the apis<p>
 *
 * @author Omid Rostami
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String USER_CONTROLLER_TAG = "user controller";
    public static final String PRODUCT_CONTROLLER_TAG = "product controller";
    public static final String COMMENT_CONTROLLER_TAG = "product controller";
    public static final String PRODUCT_PER_CATEGORY_CONTROLLER_TAG = "product per category controller";


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                //todo complete the below line with proper description
                .tags(
                        new Tag(USER_CONTROLLER_TAG, "user controller"),
                        new Tag(PRODUCT_PER_CATEGORY_CONTROLLER_TAG, "product per category controller"),
                        new Tag(COMMENT_CONTROLLER_TAG, "comment controller"),
                        new Tag(PRODUCT_CONTROLLER_TAG, "product controller "))
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "EGS",
                "This application provides api(s) to create and maintain services",
                "1.0",
                "all right reserved to",
                new Contact("Omid Rostami", "", "rostami.od@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }

}
