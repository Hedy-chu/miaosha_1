package com.miaosha_1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qqtang
 * @date 2020.9.1
 */
@Setter
@Getter
@Data
public class MiaoshaGoods implements Serializable {
    private Long id;
    private Long goodsId;
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
