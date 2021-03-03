package com.gsshop.beans;

import lombok.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ValidateTestBean {

    public ValidateTestBean(){
        System.out.println("Session Bean here!!! Hyunsoo log ValidatorTestBean");
    }

    @Size(min=4, max=10)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String data1;

    @Size(min=4, max=10)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String data2;
}
