package com.secure.secure_back_end.configuration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration
{
    @Bean
    public Docket docket2()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.secure.secure_back_end"))
                .paths(PathSelectors.regex("/authorities.*|/users.*|/admins.*|/projects.*|/tickets.*|/comments.*|/heroku.*"))
                .build().apiInfo(metaInfo());
    }


    private ApiInfo metaInfo()
    {
        return new ApiInfo(
                "RESTful API for a Bug-Tracker application",
                "Uses JWTs for security, has different. Supports different Roles with different privileges. It's manin purpose is to track bugs for diferent projects",
                "1.0",
                "ToS undefined for now",
                new Contact("Aleks", "no url for now", "aleksander.dorkov@gmail.com"),
                "Apace Licence Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}

