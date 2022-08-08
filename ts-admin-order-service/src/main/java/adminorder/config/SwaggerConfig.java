package adminorder.config;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import edu.fudan.common.annotation.SwaggerDisplayEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.schema.Annotations;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.schema.ApiModelProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev", matchIfMissing = true)
public class SwaggerConfig implements ModelPropertyBuilderPlugin {

    @Value("${swagger.controllerPackage}")
    private String controllerPackagePath;
//    @Value("${swagger.enable}")
//    private Boolean swaggerEnable;

    private static final Logger LOGGER = LoggerFactory.getLogger(edu.fudan.common.config.SwaggerConfig.class);
    /**
     * 创建swagger API文档，访问地址http://host:port/productName/swagger-ui/
     * <p>1、apiInfo()：增加API相关的信息.
     * <p>2、select()：返回ApiSelectorBuilder实例，用于控制哪些接口暴露给swagger来展现.
     * <p>3、apis()：指定扫描的路径来指定要建立的API目录.
     *     RequestHandlerSelectors 用于配置要扫描的包，以下参数可选：
     *         basePackage()：指定要扫描的包.
     *         any()：扫描全部.
     *         none()：都不扫描.
     *         withMethodAnnotation：扫描方法上的注解, 参数是一个注解的反射对象.
     *         withClassAnnotation：扫描类上的注解, 参数是一个注解的反射对象.
     *             例如：withClassAnnotation(RestController .class) 只扫描类上有@RestController的生成文档.
     * <p>4、paths()：过滤什么路径.
     */
    @Bean
    public Docket createRestApi() {
        SwaggerConfig.LOGGER.info("====-- {}", controllerPackagePath);
        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage(controllerPackagePath))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
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

    //    -----------------------自定义显示enum的注解--------------------------------
    @Override
    public void apply(ModelPropertyContext context) {
        //如果不支持swagger的话，直接返回
//        if (!swaggerEnable) {
//            return;
//        }

        //获取当前字段的类型
        final Class fieldType = context.getBeanPropertyDefinition().get().getField().getRawType();

        //为枚举字段设置注释
        descForEnumFields(context, fieldType);
    }

    /**
     * 为枚举字段设置注释
     */
    private void descForEnumFields(ModelPropertyContext context, Class fieldType) {
        com.google.common.base.Optional<ApiModelProperty> annotation = Optional.absent();

        // 找到 @ApiModelProperty 注解修饰的枚举类
        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation
                    .or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(Annotations.findPropertyAnnotation(
                    context.getBeanPropertyDefinition().get(),
                    ApiModelProperty.class));
        }

        //没有@ApiModelProperty 或者 notes 属性没有值，直接返回
        if (!annotation.isPresent() || StringUtils.isEmpty((annotation.get()).notes())) {
            return;
        }

        //@ApiModelProperties中的notes指定的class类型
        Class rawPrimaryType;
        try {
            rawPrimaryType = Class.forName((annotation.get()).notes());
        } catch (ClassNotFoundException e) {
            //如果指定的类型无法转化，直接忽略
            return;
        }

        Object[] subItemRecords = null;
        SwaggerDisplayEnum swaggerDisplayEnum = AnnotationUtils
                .findAnnotation(rawPrimaryType, SwaggerDisplayEnum.class);
        // 判断是否存在 @SwaggerDisplayEnum 注解，并且 rawPrimaryType 是枚举
        if (null != swaggerDisplayEnum && Enum.class.isAssignableFrom(rawPrimaryType)) {
            // 拿到枚举的所有的值
            subItemRecords = rawPrimaryType.getEnumConstants();
        }
        if (null == subItemRecords) {
            return;
        }

        final List<String> displayValues =
                Arrays.stream(subItemRecords)
                        .filter(Objects::nonNull)
                        // 调用枚举类的 toString 方法
                        .map(Object::toString)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        String joinText = " (" + String.join("; ", displayValues) + ")";
        try {
            // 拿到字段上原先的描述
            Field mField = ModelPropertyBuilder.class.getDeclaredField("description");
            mField.setAccessible(true);
            // context 中的 builder 对象保存了字段的信息
            joinText = mField.get(context.getBuilder()) + joinText;
        } catch (Exception e) {
            SwaggerConfig.LOGGER.error(e.getMessage());
        }

        // 设置新的字段说明并且设置字段类型
        final ResolvedType resolvedType = context.getResolver().resolve(fieldType);
        context.getBuilder().description(joinText).type(resolvedType);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

}
