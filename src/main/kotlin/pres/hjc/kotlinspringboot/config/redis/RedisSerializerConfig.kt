package pres.hjc.kotlinspringboot.config.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/29
@time 11:12
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableCaching
class RedisSerializerConfig :CachingConfigurerSupport(){

    private  val log by lazy { LoggerFactory.getLogger(RedisSerializerConfig::class.java) }
    /**
     * redis序列化
     */
    @Bean
    fun redisTemplate(redisConnection:RedisConnectionFactory?):RedisTemplate<String,Any>{
        log.info("redis Template..")
        val template = RedisTemplate<String,Any>()
        template.setConnectionFactory(redisConnection!!)
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        val serializable:Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(Any::class.java)
        val mapper = ObjectMapper()

        mapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY)
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL) //弃用
//        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL)
        serializable.setObjectMapper(mapper)

        // 值采用json序列化
        template.valueSerializer = serializable
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer = StringRedisSerializer()

        // 设置hash key 和value序列化模式
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = serializable
        template.afterPropertiesSet()
        return template
    }

    /**
     * chahe缓存
     */
    fun cacheManager(redisConnection: RedisConnectionFactory?):CacheManager{
        log.info("cache Manager ..")
        var config:RedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        config = config.entryTtl(Duration.ofMinutes(60*60*20*24 .toLong()))

        // 设置一个初始化的缓存空间set集合
        val cacheNames:MutableSet<String> = HashSet()
        cacheNames.add("order_pay")

        // 对每个缓存空间应用不同的配置
        val configMap:MutableMap<String,RedisCacheConfiguration> = HashMap()
        configMap["order_pay"] = config.entryTtl(Duration.ofSeconds(60 * 60 * 24 * 4.toLong()))

        return RedisCacheManager.builder(redisConnection!!)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build()
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    /*@Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }*/

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    /*@Bean
    fun valueOperations(redisTemplate:RedisTemplate<String, Any>): ValueOperations<String, Any>{
        return redisTemplate.opsForValue()
    }*/
    /*
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }*/

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    /*@Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }*/

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    /*@Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }*/

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    /*@Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }*/
}

