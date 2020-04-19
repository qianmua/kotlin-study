package pres.hjc.kotlinspringboot.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 14:58
 */
@Slf4j
public class RabbitReturnConfig {

    /**
     * 回调函数setConfirmCallback
     * 回调函数setReturnCallback
     *
     * 推送消息存在四种情况：
     * 1、消息推送到server，但是在server里找不到交换机 -> 触发的是 ConfirmCallback 回调函数
     * 2、消息推送到server，找到交换机了，但是没找到队列 -> 两个函数都被调用了 (在路由分发给队列的时候，找不到队列，所以报了错误 NO_ROUTE )
     * 3、消息推送到sever，交换机和队列啥都没找到  -> 这种情况触发的是 ConfirmCallback 回调函数
     * 4、消息推送成功 -> success
     * */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //开启回调函数
        rabbitTemplate.setMandatory(true);
        /*生产者*/
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause) ->{
            log.info("confirmCallback\t相关数据" + correlationData);
            log.info("confirmCallback\t确认" + ack);
            /*失败原因*/
            log.info("confirmCallback\t原因" + cause);
        });
        /*回调函数*/
        rabbitTemplate.setReturnCallback((message,replyCode,replyText,exchange,routingKey) -> {
            log.info("returnCallback\t消息" + message);
            log.info("returnCallback\t回应码" + replyCode);
            log.info("returnCallback\t回应信息" + replyText);
            log.info("returnCallback\t交换机" + exchange);
            log.info("returnCallback\t路由键" + routingKey);
        });
        return rabbitTemplate;
    }
}
