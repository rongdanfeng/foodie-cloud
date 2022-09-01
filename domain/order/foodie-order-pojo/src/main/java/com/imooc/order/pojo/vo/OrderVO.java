package com.imooc.order.pojo.vo;

import com.imooc.pojo.ShopcartBO;

import java.util.List;

public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
    // 保存Redis需要清除的商品集合
    private List<ShopcartBO> needRemoveList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MerchantOrdersVO getMerchantOrdersVO() {
        return merchantOrdersVO;
    }

    public void setMerchantOrdersVO(MerchantOrdersVO merchantOrdersVO) {
        this.merchantOrdersVO = merchantOrdersVO;
    }

    public List<ShopcartBO> getNeedRemoveList() {
        return needRemoveList;
    }

    public void setNeedRemoveList(List<ShopcartBO> needRemoveList) {
        this.needRemoveList = needRemoveList;
    }
}