package net.atos.springcachingexample;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@EnableCaching
@Configuration
public class ApplicationCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache booksCache = new ConcurrentMapCache("pizzas");
        cacheManager.setCaches(Arrays.asList(booksCache));
        return cacheManager;
    }
}
