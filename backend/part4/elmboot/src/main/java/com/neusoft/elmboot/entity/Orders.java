package com.neusoft.elmboot.entity;

import com.neusoft.elmboot.dto.OrderDetailet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class Orders implements Serializable {
    private Integer orderId;
    private String userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId; //送货地址编号
    private Integer orderState; //订单状态（0：未支付； 1：已支付）

    //一对多：订单明细
    private List<OrderDetailet> list;

}
