package cn.cachalot.intelligentattendancesystem.config;

import cn.cachalot.intelligentattendancesystem.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration //这是一个配置类
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源映射 前一个参数是访问路径，后一个参数是文件路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
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
}
