package com.imooc.item.service;


import com.imooc.item.pojo.Items;
import com.imooc.item.pojo.ItemsImg;
import com.imooc.item.pojo.ItemsParam;
import com.imooc.item.pojo.ItemsSpec;
import com.imooc.item.pojo.vo.CommentLevelCountsVO;
import com.imooc.item.pojo.vo.ShopcartVO;
import com.imooc.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 接口层要对外提供服务，当使用Feign组件时，调用起来更加方便
@RequestMapping("item-api")
public interface ItemService {

    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    @GetMapping("item")
    Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * 查询商品图片列表
     * @param itemId
     * @return
     */
    @GetMapping("itemImages")
    List<ItemsImg> queryItemImgList(@RequestParam("itemId")String itemId);

    /**
     * 查询商品规格信息
     * @param itemId
     * @return
     */
    @GetMapping("itemSpecs")
    List<ItemsSpec> queryItemSpecList(@RequestParam("itemId")String itemId);

    /**
     * 查询商品参数信息
     * @param itemId
     * @return
     */
    @GetMapping("itemParam")
    ItemsParam queryItemParam(@RequestParam("itemId")String itemId);

    /**
     * 查询评价数量
     * @param itemId
     * @return
     */
    @GetMapping("commentCounts")
    CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId")String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId
     * @param level
     * @return
     */
    @GetMapping("pagedComments")
    public PagedGridResult queryPagedComments(@RequestParam("itemId")String itemId,
                                              @RequestParam("level")Integer level,
                                              @RequestParam("page")Integer page,
                                              @RequestParam("pageSize")Integer pageSize);

    /**
     * 根据商品规格id查询商品
     * @param itemSpecIds
     * @return
     */
    @GetMapping("getCartBySpecIds")
    List<ShopcartVO> queryItemsBySpecIds(@RequestParam("itemSpecIds")String itemSpecIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    @GetMapping("itemSpec")
    public ItemsSpec queryItemSpecById(@RequestParam("specId")String specId);

    /**
     * 根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    @GetMapping("itemImage")
    public String queryItemMainImgById(@RequestParam("itemId")String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    @PostMapping("decreaseStock")
    public void decreaseItemSpecStock(@RequestParam("specId")String specId, @RequestParam("buyCounts")int buyCounts);
}
