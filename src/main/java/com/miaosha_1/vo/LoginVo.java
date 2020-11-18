package com.miaosha_1.vo;

import com.miaosha_1.validator.isMobile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author qqtang
 * @Date 2020/11/17 15:17
 * @Desc
 */
@Getter
@Setter
@Data
public class LoginVo {
    @NotNull
    @isMobile
    private String mobile;
    @NotNull
    @Length(min = 2)
    private String password;
}
