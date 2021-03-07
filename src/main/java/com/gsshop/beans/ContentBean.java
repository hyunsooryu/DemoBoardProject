package com.gsshop.beans;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentBean {
    private int contentIdx;

    @NotBlank
    private String contentSubject;

    @NotBlank
    private String contentText;

    private MultipartFile uploadFile;

    private String contentFile;

    private int contentWriterIdx;
    private int contentBoardIdx;
    private String contentDate;
}
