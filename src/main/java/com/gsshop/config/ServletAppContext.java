package com.gsshop.config;


import com.gsshop.interceptor.TopMenuInterceptor;
import com.gsshop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

//Spring MVC 프로젝트에 관련한 설정을 하는 클래스입니다.
@Configuration
@EnableWebMvc
@ComponentScan(value = {"com.gsshop.controller"})
public class ServletAppContext implements WebMvcConfigurer {

    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    //리소스에 관한 설정을 지정합니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**")
                .addResourceLocations("/resources/");

    }

    //뷰 리졸버에 대한 설정을 진행합니다.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }


    //인터셉터를 등록합니다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(boardService);
        WebMvcConfigurer.super.addInterceptors(registry);
        InterceptorRegistration regTopMenuList = registry.addInterceptor(topMenuInterceptor);
        regTopMenuList.addPathPatterns("/**");
    }

    //메세지를 등록해서 사용
    @Bean
    ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        return res;
    }


}
