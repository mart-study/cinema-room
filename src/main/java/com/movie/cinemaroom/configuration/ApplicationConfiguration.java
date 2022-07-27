package com.movie.cinemaroom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ApplicationConfiguration {

	@Bean
	RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
	  RedisTemplate<?, ?> template = new RedisTemplate<>();
	  template.setConnectionFactory(connectionFactory);

	  return template;
	}
}
