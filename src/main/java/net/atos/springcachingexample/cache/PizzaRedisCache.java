package net.atos.springcachingexample.cache;

import lombok.extern.slf4j.Slf4j;
import net.atos.springcachingexample.model.Pizza;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PizzaRedisCache {

    private final ValueOperations<String, Pizza> cache;

    public PizzaRedisCache(RedisTemplate redisTemplate) {
        this.cache = redisTemplate.opsForValue();
    }

    public Optional<Pizza> get(String key) {
        try {
            return Optional.ofNullable(this.cache.get(key));
        } catch (Exception e) {
            log.error("Error get from redis", e);
            return Optional.empty();
        }
    }

    public void put (String key, Pizza pizza){
        try {
            this.cache.set(key, pizza, 2, TimeUnit.MINUTES);
        }catch (Exception e){
            log.error("Error pot value to redis", e);
        }
    }
}
