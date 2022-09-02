package com.imooc.cart.service;

import com.imooc.pojo.ShopcartBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("cart-api")
public interface CartService {

    @PostMapping("/addItem")
    Boolean addItemToCart(@RequestParam("userId") String userId,
             @RequestBody ShopcartBO shopcartBO);

    @PostMapping("/delItem")
    Boolean delItemToCart(@RequestParam("userId") String userId,
             @RequestParam("itemSpecId") String itemSpecId);

    @DeleteMapping("/clearCart")
    Boolean clearCart(@RequestParam("userId") String userId);
}
