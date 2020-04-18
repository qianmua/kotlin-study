package pres.hjc.kotlinspringboot.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/18
 * @time 13:56
 */
@Configuration
public class DirectRabbitConfig {

    /**
     * 创建队列并且持久化
     * @return
     */
    @Bean
    public Queue createDirectQueue(){
        return new Queue("psaq1",true);
    }

    @Bean
    DirectExchange createChange(){
        return new DirectExchange("psaChange");
    }

    Binding bingDirect(){
        return BindingBuilder.bind(createDirectQueue()).to(createChange()).with("DirectRouting");
    }



}
