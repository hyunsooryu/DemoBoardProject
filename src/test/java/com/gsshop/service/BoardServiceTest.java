package com.gsshop.service;

import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.ContentBean;
import com.gsshop.config.RootAppContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    @Test
    @DisplayName("보드의 내용을 입력하는 테스트입니다. ")
    void insert_write_content_bean(){
        boardService.addContentInfo(ContentBean.builder()
                        .contentBoardIdx(0)
                        .contentWriterIdx(1)
                        .contentFile("demotest.jpg")
                        .contentSubject("demotest")
                        .contentText("demotest입니다. 어려움이 많지만 잘 극복해내겠습니다.")
                        .uploadFile(new MultipartFile() {
                            @Override
                            public String getName() {
                                return null;
                            }

                            @Override
                            public String getOriginalFilename() {
                                return null;
                            }

                            @Override
                            public String getContentType() {
                                return null;
                            }

                            @Override
                            public boolean isEmpty() {
                                return false;
                            }

                            @Override
                            public long getSize() {
                                return 0;
                            }

                            @Override
                            public byte[] getBytes() throws IOException {
                                return new byte[0];
                            }

                            @Override
                            public InputStream getInputStream() throws IOException {
                                return null;
                            }

                            @Override
                            public void transferTo(File file) throws IOException, IllegalStateException {

                            }
                        })
                        .build()
        );
    }

    @Test
    void getBoardContentList(){
        List<ContentBean> contentBeanList = boardService.getContentBeanList(0);
        assertNotNull(contentBeanList);
        contentBeanList.stream().forEach(System.out::println);
    }

}