package com.miaosha_1.vo;

import com.miaosha_1.domain.Goods;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qqtang
 * @Date 2020/11/19 15:02
 * @Desc
 */
@Setter
@Getter
@Data
public class GoodsVo extends Goods implements Serializable {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
