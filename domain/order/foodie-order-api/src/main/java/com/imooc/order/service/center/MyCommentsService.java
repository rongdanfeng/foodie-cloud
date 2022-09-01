package com.imooc.order.service.center;

import com.imooc.order.pojo.OrderItems;
import com.imooc.order.pojo.bo.center.OrderItemsCommentBO;
import com.imooc.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequestMapping("order-comments-api")
public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId
     * @return
     */
    @GetMapping("pendingComment")
    List<OrderItems> queryPendingComment(@RequestParam("orderId") String orderId);

    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param orderItemList
     */
    @PostMapping("comments")
    void saveCommentList(@RequestParam("userId") String userId,
                         @RequestParam("orderId") String orderId,
                         @RequestBody List<OrderItemsCommentBO> orderItemList);


}
