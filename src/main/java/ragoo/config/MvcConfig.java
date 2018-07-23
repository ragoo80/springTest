package ragoo.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
*/


import ragoo.initializer.AppInitialzer;
import ragoo.interceptor.CorsInterceptor;
import ragoo.interceptor.SampleInterceptor;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableAsync // @Async 어노테이션을 사용하기 위함
@ComponentScan(
        basePackages={"ragoo"},
        includeFilters={
                @ComponentScan.Filter(Controller.class),
                @ComponentScan.Filter(Service.class),
                @ComponentScan.Filter(Repository.class),

        },
        excludeFilters={
//                @ComponentScan.Filter(Configuration.class),
//                @ComponentScan.Filter(Service.class),
//                @ComponentScan.Filter(Repository.class)


        }
)

public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(AppInitialzer.class);

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "/static/", "classpath:/static/", "C:/static/", "file:/static/"
    };




    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {

        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(2);
        return resolver;
    }



    /*@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/");
        super.addResourceHandlers(registry);
    }*/


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/*").addResourceLocations("static");
        //registry.addResourceHandler("/resources/*").addResourceLocations("resources").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }




}
