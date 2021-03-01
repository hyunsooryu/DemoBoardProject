package com.gsshop.config;


import com.gsshop.interceptor.TopMenuInterceptor;
import com.gsshop.service.BoardService;
import com.gsshop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
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

    //메세지만 붙여넣으면, 프로퍼티 못읽어.. 그걸 방지하는 코드입니다.
    @Bean
    public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfiguer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    //메세지를 등록해서 사용
    @Bean
    ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("classpath:/properties/error_message");
        res.setCacheSeconds(3);
        res.setDefaultEncoding("UTF-8");
        return res;
    }



    //Validator 설정
    @Bean("userValidator")
    Validator userValidator(){
        Validator userValidator = new UserValidator();
        return userValidator;
    }




}
