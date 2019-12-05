package net.atos.springcachingexample.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class PizzaRedisCacheConfig {

    @Bean
    PizzaRedisCache pizzaRedisCache(RedisTemplate redisTemplate) {
        return new PizzaRedisCache(redisTemplate);
    }
}
