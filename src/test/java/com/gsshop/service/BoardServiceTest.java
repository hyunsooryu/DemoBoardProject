package com.gsshop.service;

import com.gsshop.beans.BoardInfoBean;
import com.gsshop.config.RootAppContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringJUnitConfig(RootAppContext.class)
class BoardServiceTest {

    final BoardService boardService;

    @Autowired
    BoardServiceTest(BoardService boardService){
        this.boardService = boardService;
    }

    @Test
    void injection_test(){
        assertNotNull(boardService);
    }

    @Test
    @DisplayName("상단 보드 인포를 가져오는 테스트입니다. cache=true")
    void getBoardInfoList(){
        assertNotNull(boardService.getBoardInfoList());
    }


}