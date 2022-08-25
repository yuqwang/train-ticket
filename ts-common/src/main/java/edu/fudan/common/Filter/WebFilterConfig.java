package edu.fudan.common.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebFilterConfig implements WebMvcConfigurer {
    @Autowired
    private MyFilter myFilter;

    @Bean("myFilterBean")
    public FilterRegistrationBean myFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myFilter);
        registration.addUrlPatterns("/api/v1/*");
        registration.setName("myFilter");
        return registration;
    }
}
