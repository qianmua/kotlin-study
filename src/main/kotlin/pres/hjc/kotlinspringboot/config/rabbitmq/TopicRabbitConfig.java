package pres.hjc.kotlinspringboot.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 14:15
 */
@Configuration
public class TopicRabbitConfig {
    public final static String TOPIC_M = "rbmq.man";
    public final static String TOPIC_WM = "rbmq.woman";

    @Bean
    public Queue man(){
        return new Queue(TOPIC_M);
    }
    @Bean
    public Queue woman(){
        return new Queue(TOPIC_WM);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * 将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     * with 配置路由键。
     * @return
     */
    @Bean
    Binding bindingTopic(){
        return BindingBuilder.bind(man()).to(exchange()).with(TOPIC_M);
    }

    /**
     * 将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    Binding bindingTopic2(){
        return BindingBuilder.bind(woman()).to(exchange()).with("rbmq.#");
    }


}

