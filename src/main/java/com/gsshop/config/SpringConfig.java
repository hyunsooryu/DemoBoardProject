package com.gsshop.config;


import com.gsshop.config.filter.hyunsooFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

//추상클래스인 애노테이션컨피그디스패처서블릿이니셜라이저를 사용
//web.xml 과 같은 역할을 한다고 생각하면됩니다.
public class SpringConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootAppContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletAppContext.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter, new hyunsooFilter()};
    }

    //멀티파트 폼 데이터를 주입받기 위해 만드는 것입니다.
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
        //1.임시장소, 2.업로드파일 최대용량, 3.파일데이터를 포함한 전체 요청 정보의 최대용량,
        registration.setMultipartConfig(config1);
    }
}
