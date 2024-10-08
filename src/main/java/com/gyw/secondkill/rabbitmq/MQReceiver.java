package com.gyw.secondkill.rabbitmq;

import com.gyw.secondkill.domain.MiaoshaOrder;
import com.gyw.secondkill.domain.MiaoshaUser;
import com.gyw.secondkill.redis.RedisService;
import com.gyw.secondkill.result.CodeMsg;
import com.gyw.secondkill.result.Result;
import com.gyw.secondkill.service.GoodsService;
import com.gyw.secondkill.service.MiaoshaService;
import com.gyw.secondkill.service.OrderService;
import com.gyw.secondkill.vo.GoodsVo;
import com.gyw.secondkill.domain.MiaoshaOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dell
 * @create 2019-07-28 15:03
 */
@Service
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    /**
     * Direct模式，Exchange交换机
     * @param message
     */

   @RabbitListener(queues=MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        logger.info("receive message:" + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId); // 这里是从数据库中判断
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }

       // 判断是否已经秒杀过了
       MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
       if(order != null) {
           return;
       }

       //减库存 下订单 写入秒杀订单
       miaoshaService.miaosha(user, goods);
   }

   /*
    @RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message) {
        logger.info("receive message:"+message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        logger.info(" topic  queue1 message:"+message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        logger.info(" topic  queue2 message:"+message);
    }

    @RabbitListener(queues=MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        logger.info(" header  queue message:" + new String(message));
    }*/

}
