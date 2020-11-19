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
public class OrderInfo implements Serializable {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer orderStatus;
    private Date createDate;
    private Date payDate;
}
