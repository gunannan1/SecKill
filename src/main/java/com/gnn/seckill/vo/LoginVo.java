package com.gnn.seckill.vo;

import com.gnn.seckill.validator.MobileCheck;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {
    @NotNull
    @MobileCheck
    private String mobile ;

    @NotNull
    @Length(min=32)
    private String password;

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
