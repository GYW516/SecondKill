package com.gyw.secondkill.controller;

import com.gyw.secondkill.domain.MiaoshaUser;
import com.gyw.secondkill.domain.OrderInfo;
import com.gyw.secondkill.redis.RedisService;
import com.gyw.secondkill.result.CodeMsg;
import com.gyw.secondkill.result.Result;
import com.gyw.secondkill.service.GoodsService;
import com.gyw.secondkill.service.MiaoshaUserService;
import com.gyw.secondkill.service.OrderService;
import com.gyw.secondkill.vo.GoodsVo;
import com.gyw.secondkill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dell
 * @create 2019-07-24 17:43
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user, @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SERVER_SERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }

        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
