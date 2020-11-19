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
public class MiaoshaOrder implements Serializable {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
