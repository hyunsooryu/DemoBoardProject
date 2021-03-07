package com.gsshop.dao;


import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.ContentBean;
import com.gsshop.beans.UserBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDao {
    private final SqlSessionTemplate sqlSessionTemplate;

    BoardDao(SqlSessionTemplate sqlSessionTemplate){
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public Optional<List<BoardInfoBean>> getBoardInfoList(){
        List<BoardInfoBean> boardInfoList = sqlSessionTemplate.selectList("board.get_board_list");
        Optional<List<BoardInfoBean>> optional = Optional.ofNullable(boardInfoList);
        return optional;
    }

    public void insertBoardContentInfo(ContentBean writeContentBean){
        sqlSessionTemplate.insert("board.insert_board_content_bean", writeContentBean);
    }

    public Optional<List<ContentBean>> getBoardContentList(int boardInfoIdx){
        List<ContentBean> contentBeanList = sqlSessionTemplate.selectList("get_board_content_bean_list", boardInfoIdx);
        return Optional.ofNullable(contentBeanList);
    }
}
