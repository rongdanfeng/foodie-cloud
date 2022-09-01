package com.imooc.order.service;


import com.imooc.order.pojo.OrderStatus;
import com.imooc.order.pojo.bo.PlaceOrderBO;
import com.imooc.order.pojo.bo.SubmitOrderBO;
import com.imooc.order.pojo.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("order-api")
public interface OrderService {

    /**
     * 创建订单
     * @param placeOrderBO
     * @return
     */
    @PostMapping("order")
    OrderVO createOrder(@RequestBody PlaceOrderBO placeOrderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    @PostMapping("updateStatus")
    public void updateOrderStatus(@RequestParam("orderId") String orderId,
                                  @RequestParam("orderStatus") Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    @GetMapping("orderStatus")
    OrderStatus getOrderStatus(@RequestParam("orderId") String orderId);

    /**
     * 关闭超时未支付订单
     */
    @DeleteMapping("closePendingOrders")
    void closeOrder();
}
