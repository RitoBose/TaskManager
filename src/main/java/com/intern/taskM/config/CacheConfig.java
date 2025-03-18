package com.intern.taskM.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    //we can add additional configuration for Redis if needed.
}
