package com.gsshop.dao;


import com.gsshop.beans.BoardInfoBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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
}
