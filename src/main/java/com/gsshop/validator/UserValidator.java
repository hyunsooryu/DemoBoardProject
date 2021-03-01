package com.gsshop.validator;

import com.gsshop.beans.UserBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        //isAssignableFrom은 런타임 때 동적으로 확인할 때 쓰입니다.
        return UserBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        errors.getAllErrors().stream()
              .forEach(error->System.out.println(error.getCodes()[0]));
        UserBean userBean = (UserBean)o;
        //joinUserBean일 경우에만 진행
        System.out.println("HYUNSOO LOG POINT BBB");
        if(errors.getObjectName().equals("joinUserBean")) {
            if (!userBean.getUserPw().equals(userBean.getUserPw2())) {
                errors.rejectValue("userPw", "NotEquals");
                errors.rejectValue("userPw2", "NotEquals");
            }

            if(userBean.isUserIdExist()){
                errors.rejectValue("userId","DontCheckUserIdExist");
            }
        }
    }
}
