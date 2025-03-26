package net.eson.mp3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Eson
 * @date 2025年03月24日 14:02
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:D:/bible/upload/images/");
        registry.addResourceHandler("/uploads/txt/**")
                .addResourceLocations("file:D:/bible/upload/txt/");
        registry.addResourceHandler("/uploads/mp3/**")
                .addResourceLocations("file:D:/bible/upload/mp3/");
    }
}
    