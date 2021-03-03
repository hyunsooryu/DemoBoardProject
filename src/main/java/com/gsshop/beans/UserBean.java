package com.gsshop.beans;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserBean implements Serializable {


    public UserBean(){
        System.out.println("Hello, I am UserBean !!! hyunsoo Log");
        this.userIdExist = true;
        this.userLogin = false;
    }

    @ToString.Exclude
    private static final long serialVersionUID = 2L;

    private int userIdx;


    @Size(min = 2, max=4)
    @Pattern(regexp = "[가-힣]*")
    private String userName;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String userId;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String userPw;
    //회원가입 확인용 비밀번호 입니다.

    @ToString.Exclude
    private boolean userIdExist;

    @ToString.Exclude
    private boolean userLogin;

    @ToString.Exclude
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String userPw2;

}
