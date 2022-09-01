package com.imooc.order.pojo.bo;

import com.imooc.pojo.ShopcartBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于创建已经下订单的BO对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderBO {
    private SubmitOrderBO order;
    private List<ShopcartBO> items;


}
