package com.example.demo.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	/*
	 * Configuracion que vamos a trabajar en este caso person
	 * RedissonSpringCacheManager cacheManager
	 */
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("person");
	}
	
//	@Bean
//	public CacheManager cacheManager(RedissonClient redisonClient) {
//		Map<String, CacheConfig> config= new HashMap<>();
//		config.put("person", new CacheConfig());
//		return new RedissonSpringCacheManager(redisonClient);
//	}

	/*
	 * Configuracion para redis
	 * Url donde se levanto redis 
	 * "redis://127.0.0.1:6379"
	 */
//	@Bean(destroyMethod = "shutdown")
//	public RedissonClient redisson() {
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//		return Redisson.create(config);
//	}

}
