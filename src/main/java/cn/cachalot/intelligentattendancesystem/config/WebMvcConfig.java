package cn.cachalot.intelligentattendancesystem.config;

import cn.cachalot.intelligentattendancesystem.common.JacksonObjectMapper;
import cn.cachalot.intelligentattendancesystem.interceptor.LoginInterceptor;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Slf4j
@Configuration //这是一个配置类
@EnableSwagger2
@EnableKnife4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源映射 前一个参数是访问路径，后一个参数是文件路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //super.addResourceHandlers(registry);
    }

    /**
     * 扩展spring mvc消息转换器 解决 js 雪花算法ID问题
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展spring mvc消息转换器");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器,底层使用Jackson将Java对象转换为json
        converter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象添加到mvc转换器集合中
        converters.add(0, converter);
    }

    /**
     * swagger2 配置
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("cn.cachalot.intelligentattendancesystem.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("智能考勤系统").description("智能考勤系统").termsOfServiceUrl("http://8.142.66.212/").version("1.0").build();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPattern 添加拦截规则 /** 拦截所有包括静态资源
        // excludePathPattern 排除拦截规则 所以我们需要放开静态资源的拦截
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login",
                "/user" + "/login", "/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs");
    }

}


