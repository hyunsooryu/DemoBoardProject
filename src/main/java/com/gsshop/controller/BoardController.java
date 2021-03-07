package com.gsshop.controller;


import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.ContentBean;
import com.gsshop.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.gsshop.utils.GateWayUtils.SUCCESS_FLG;
import static com.gsshop.utils.GateWayUtils.WRITE_PRO;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/main")
    public String main(@RequestParam("boardInfoIdx") int boardInfoIdx, ModelMap model){
        System.out.println(boardInfoIdx);

        //1. 게시판 이름 세팅 / 캐쉬
        if(0 <= boardInfoIdx && boardInfoIdx <= 3) {
            model.addAttribute("boardName",
                    boardService.getBoardInfoList().get(boardInfoIdx).getBoardInfoName());
        }else{
            model.addAttribute("boardName",
                    boardService.getBoardInfoList().get(0).getBoardInfoName());
        }
        //TODO 2. 게시글 세팅

        model.addAttribute("boardInfoIdx", boardInfoIdx);
        return "board/main";
    }

    @GetMapping("/read")
    public String read(){
       return "board/read";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("writeContentBean")ContentBean writeContentBean){
       return "board/write";
    }

    @PostMapping("/write_pro")
    public String write_pro(@Valid @ModelAttribute("writeContentBean")ContentBean writeContentBean, BindingResult result,
                            ModelMap model){
        if(result.hasErrors()){
            return "board/write";
        }

        boardService.addContentInfo(writeContentBean);

        model.addAttribute(SUCCESS_FLG, WRITE_PRO);
        return "board/gateway";
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
