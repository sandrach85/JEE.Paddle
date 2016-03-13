package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import business.api.Uris;
import business.controllers.TokenValidateAccessInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {ResourceNames.REST_API, ResourceNames.CONTROLLERS, ResourceNames.DAOS, ResourceNames.SERVICES})
public class WebConfig extends WebMvcConfigurerAdapter {

    // CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").maxAge(3600);
    }
    
    // Se configuran los interceptores
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenValidateAccessInterceptor()).addPathPatterns(Uris.SERVLET_MAP + "/**")
                .excludePathPatterns("/foo/**");
    }

}
