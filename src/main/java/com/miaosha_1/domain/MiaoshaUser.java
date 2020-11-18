package com.miaosha_1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author qqtang
 * @Date 2020/11/17 16:07
 * @Desc
 */
@Setter
@Getter
@Data
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
