package foodsearch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.controllerPackage}")
    private String controllerPackagePath;

    private static final Logger LOGGER = LoggerFactory.getLogger(edu.fudan.common.config.SwaggerConfig.class);

    @Bean
    public Docket createRestApi() {
        SwaggerConfig.LOGGER.info("====-- {}", controllerPackagePath);
        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .pathProvider(new RelativePathProvider() {
//                    @Override
//                    public String getApplicationBasePath() {
//                        return "/test"+ super.getApplicationBasePath();
//                    }
//                })
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage(controllerPackagePath))
                //指定路径处理PathSelectors.any()代表所有的路径
//                .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/api/v1/foodservice/foods/{date}/{startStation}/{endStation}/{tripId}"))
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("train-ticket SpringBoot Swagger2")
                //文档描述
                .description("API Specification")
                //服务条款URL
                .termsOfServiceUrl("https://github.com/FudanSELab/train-ticket")
                //版本号
                .version("1.0.0")
                .build();
    }
}
