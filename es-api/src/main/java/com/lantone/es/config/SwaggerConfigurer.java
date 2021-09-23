package com.lantone.es.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;


/**
 * @Description: Swagger配置类
 * @author: kongwz
 * @time: 2018/8/2 14:21
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger", value = { "enable" }, havingValue = "true")
@EnableSwagger2
public class SwaggerConfigurer {
    /**
     * 全局参数
     *
     * @return
     */
    private List<Parameter> parameter() {
        List<Parameter> params = new ArrayList<>();
        /*params.add(new ParameterBuilder().name("Authorization")
                .description("Authorization Bearer token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());
        params.add(new ParameterBuilder().name("appKeyId")
                .description("产品调用id")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());
        params.add(new ParameterBuilder().name("appKeySecret")
                .description("产品调用密码")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());*/
        return params;
    }


    @Bean
    public Docket sysApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.es.web"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameter());
        //.securitySchemes(newArrayList(oauth()))
        // .securityContexts(newArrayList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("es api ")
                .description("es服务")
                .termsOfServiceUrl("")
                .contact(new Contact("es", "", ""))
                .version("1.0")
                .build();
    }

}
