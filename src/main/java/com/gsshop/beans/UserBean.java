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
public class UserBean implements Serializable {
    private static final long serialVersionUID = 2L;
    private int userIdx;
    private String userName;
    private String userId;
    private String userPw;
    //회원가입 확인용 비밀번호 입니다.
    @ToString.Exclude
    private String userPw2;
}
