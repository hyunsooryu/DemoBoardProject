package com.gsshop.interceptor;

import com.gsshop.beans.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gsshop.utils.GateWayUtils.*;

/*
    로그인 여부를 확인하는 인터셉터
 */
public class CheckLoginInterceptor implements HandlerInterceptor {
    //로그인 세션
    private UserBean loginUserBean;

    public CheckLoginInterceptor(UserBean loginUserBean){
        this.loginUserBean = loginUserBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!loginUserBean.isUserLogin()){
            String contextPath = request.getContextPath();
            System.out.println("contextpath : " + contextPath);
            response.sendRedirect(contextPath + "/" + GATEWAY_URL_NOT_LOGIN);
            System.out.println("HYUNSOO LOG--- TEST RESPONSE_REDIRECT");
            return false;
        }
        //로그인 했다면, 다음 단계 -> Controller로 이동하면 됩니다.
        return true;
    }
}
