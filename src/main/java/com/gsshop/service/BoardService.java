package com.gsshop.service;

import com.gsshop.annotations.RedisCacheFirst;
import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.ContentBean;
import com.gsshop.beans.UserBean;
import com.gsshop.dao.BoardDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySource(value = "classpath:/properties/${spring.profiles.active}_option.properties")
public class BoardService {

    @Value("${path.upload}")
    private String PATH_UPLOAD;

    @Resource(name="loginUserBean")
    UserBean loginUserBean;

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    @RedisCacheFirst
    public List<BoardInfoBean> getBoardInfoList(){
        List<BoardInfoBean> boardInfoList = boardDao.getBoardInfoList().orElse(null);
        return boardInfoList;
    }

    public List<ContentBean> getContentBeanList(int boardInfoIdx){
        List<ContentBean> contentBeanList =
                boardDao.getBoardContentList(boardInfoIdx)
                    .orElse(null);
        return contentBeanList;
    }


    public void addContentInfo(ContentBean writeContentBean){
        MultipartFile uploadFile = writeContentBean.getUploadFile();
        //파일 업로드는 따로 진행해줘야 합니다. DB에 넣는 것이아닙니다.
        //파일 용량이 더 크다면, save 해주는데, MultiFile -> transfer을 이용합니다.
        if(uploadFile.getSize() > 0){
          String fileName = saveUploadFile(uploadFile);
          writeContentBean.setContentFile(fileName);
        }
        writeContentBean.setContentWriterIdx(loginUserBean.getUserIdx());
        boardDao.insertBoardContentInfo(writeContentBean);
    }


    private String saveUploadFile(MultipartFile uploadFile){
        String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
        try{
           uploadFile.transferTo(new File(PATH_UPLOAD + "/" + fileName));
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileName;
    }
}
