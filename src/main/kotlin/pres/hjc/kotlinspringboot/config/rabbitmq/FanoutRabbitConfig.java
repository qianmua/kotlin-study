package pres.hjc.kotlinspringboot.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 14:27
 */
@Configuration
public class FanoutRabbitConfig {
    /**
     *  创建三个队列 ：fanout.A   fanout.B  fanout.C
     *  将三个队列都绑定在交换机 fanoutExchange 上
     *  因为是不扇型交换机, 路由键无需配置,配置也起作用
     */
    @Bean
    public Queue fanoutA(){
        return new Queue("fanout.A");
    }
    @Bean
    public Queue fanoutB(){
        return new Queue("fanout.B");
    }
    @Bean
    public Queue fanoutC(){
        return new Queue("fanout.C");
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 绑定到交换机
     * @return
     */
    @Bean
    Binding fanoutBindingA(){
        return BindingBuilder.bind(fanoutA()).to(fanoutExchange());
    }
    @Bean
    Binding fanoutBindingB(){
        return BindingBuilder.bind(fanoutB()).to(fanoutExchange());
    }
    @Bean
    Binding fanoutBindingC(){
        return BindingBuilder.bind(fanoutC()).to(fanoutExchange());
    }






}
