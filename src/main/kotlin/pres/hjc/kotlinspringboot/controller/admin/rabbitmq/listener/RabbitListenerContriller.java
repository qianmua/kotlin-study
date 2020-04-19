package pres.hjc.kotlinspringboot.controller.admin.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 14:51
 */
@Component
@RabbitListener( queues = "fanout.A")
public class RabbitListenerContriller {

    /**
     * 消费
     * @param t
     */
    @RabbitHandler
    public void process(Map t){
        System.out.println("fanout A get msg :" + t.toString());
    }
}
