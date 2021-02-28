package com.gsshop.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContentBean {
    private int contextIdx;
    private String contentSubject;
    private String contextText;
    private String contentFile;
    private int contentWriterIdx;
    private int contentBoardIdx;
    private String contentDate;
}
