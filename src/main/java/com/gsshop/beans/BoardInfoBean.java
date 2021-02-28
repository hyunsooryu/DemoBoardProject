package com.gsshop.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class BoardInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int boardInfoIdx;
    private String boardInfoName;
}
