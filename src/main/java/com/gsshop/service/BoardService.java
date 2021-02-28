package com.gsshop.service;

import com.gsshop.annotations.RedisCacheFirst;
import com.gsshop.beans.BoardInfoBean;
import com.gsshop.dao.BoardDao;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    @RedisCacheFirst
    public List<BoardInfoBean> getBoardInfoList(){
        List<BoardInfoBean> boardInfoList = boardDao.getBoardInfoList().orElse(null);
        return boardInfoList;
    }
}
