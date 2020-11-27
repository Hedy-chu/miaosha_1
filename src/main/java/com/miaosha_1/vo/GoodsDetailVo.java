package com.miaosha_1.vo;

import com.miaosha_1.domain.MiaoshaUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qqtang
 * @Date 2020/11/25 14:05
 * @Desc 网页面上传的值
 */
@Setter
@Getter
@Data
public class GoodsDetailVo {
    private GoodsVo goods ;
    //秒杀状态
    private int  miaoshaStatus;
    //倒计时
    private int remainSecond;
    private MiaoshaUser user;
}
