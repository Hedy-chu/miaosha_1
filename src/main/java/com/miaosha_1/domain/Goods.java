package com.miaosha_1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author qqtang
 * @date 2020.9.1
 */
@Getter
@Setter
@Data
public class Goods implements Serializable {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    //库存
    private Integer goodsStock;
}
