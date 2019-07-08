package ru.itis.maletskov.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itis.maletskov.converter.StringToSongConverter;
import ru.itis.maletskov.converter.StringToUserConverter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final StringToUserConverter stringToUserConverter;
    private final StringToSongConverter stringToSongConverter;

    @Value("${upload.song.path}")
    private String audioUploadPath;

    @Value("${upload.img.path}")
    private String imgUploadPath;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserConverter);
        registry.addConverter(stringToSongConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("file://" + audioUploadPath + "/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + imgUploadPath + "/");
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setMaxPageSize(20);
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(31457280);
        return resolver;
    }
}
