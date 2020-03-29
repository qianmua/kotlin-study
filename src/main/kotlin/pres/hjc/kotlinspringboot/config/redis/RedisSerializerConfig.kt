package pres.hjc.kotlinspringboot.config.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

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

    /**
     * redis序列化
     */
    @Bean
    fun redisTemplate(redisConnection:RedisConnectionFactory?):RedisTemplate<Any,Any>{
        val template = RedisTemplate<Any,Any>()
        template.setConnectionFactory(redisConnection!!)
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        val serializable:Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(Any::class.java)
        val mapper = ObjectMapper()

        mapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY)
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL) //弃用
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL)
        serializable.setObjectMapper(mapper)

        template.valueSerializer = serializable
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer = StringRedisSerializer()
        template.afterPropertiesSet()
        return template
    }

    /**
     * chahe缓存
     */
    fun cacheManager(redisConnection: RedisConnectionFactory?):CacheManager{
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

}
