package com.gsshop.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.UserBean;
import com.gsshop.service.BoardService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    private final BoardService boardService;
    private final UserBean loginUserBean;

    public TopMenuInterceptor(BoardService boardService, UserBean loginUserBean){
        this.boardService = boardService;
        this.loginUserBean = loginUserBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean> boardInfoList = boardService.getBoardInfoList();
        request.setAttribute("topMenuList", boardInfoList);
        ObjectMapper objectMapper = new ObjectMapper();
        request.setAttribute("topMenuListJson", objectMapper.writeValueAsString(boardInfoList));
        System.out.println("LOG 111111111");
        request.setAttribute("loginUserBean", loginUserBean);
        System.out.println(" I AM BOARD TOP MENU LIST INTERCEPTOR HYUNSOO LOG");
        return true;
    }
}
