package com.gsshop.controller;


import com.gsshop.beans.BoardInfoBean;
import com.gsshop.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

   @GetMapping("/main")
    public String main(){
        return "board/main";
    }

    @GetMapping("/read")
    public String read(){
       return "board/read";
    }

    @GetMapping("/write")
    public String write(){
       return "board/write";
    }

    @GetMapping("/modify")
    public String modify(){
       return "board/modify";
    }

    @GetMapping("/delete")
    public String delete(){
        return "board/delete";
    }

    @GetMapping("/topMenuList")
    @ResponseBody
    public List<BoardInfoBean> getBoardInfoList(){
        return boardService.getBoardInfoList();
    }

}
