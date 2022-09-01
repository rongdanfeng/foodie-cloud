package com.imooc.order.service.center;


import com.imooc.order.pojo.Orders;
import com.imooc.order.pojo.vo.OrderStatusCountsVO;
import com.imooc.pojo.IMOOCJSONResult;
import com.imooc.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("myorder-api")
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("order/query")
    PagedGridResult queryMyOrderList(@RequestParam("userId") String userId,
                                     @RequestParam("orderStatus") String orderStatus,
                                     @RequestParam(value = "page",required = false) Integer page,
                                     @RequestParam(value = "pageSize",required = false) Integer pageSize);

    /**
     * @Description: 订单状态 --> 商家发货
     */
    @PostMapping("order/delivered")
    void updateDeliverOrder(@RequestParam("orderId") String orderId);

    /**
     * 查询我的订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("checkUserOrder")
    Orders checkUserOrder(@RequestParam("orderId") String userId,
                          @RequestParam("orderId") String orderId);

    /**
     * 更新订单状态 —> 确认收货
     *
     * @return
     */
    @PostMapping("order/received")
    void updateConfirmOrder(@RequestParam("orderId") String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId
     * @param orderId
     * @return
     */
    @DeleteMapping("order")
    boolean updateDeleteOrder(@RequestParam("userId") String userId ,@RequestParam("orderId") String orderId);

    /**
     * 查询用户订单数
     * @param userId
     */
    @GetMapping("order/counts")
    OrderStatusCountsVO queryOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("order/trend")
    PagedGridResult queryOrderTrend(@RequestParam("orderId") String userId,
                                    @RequestParam(value = "page",required = false)  Integer page,
                                    @RequestParam(value = "pageSize",required = false)  Integer pageSize);













}