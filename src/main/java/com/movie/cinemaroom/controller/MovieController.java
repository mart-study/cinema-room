package com.movie.cinemaroom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

	 @Autowired
	 private RedisTemplate<String, String> template;
	
	 private static final String STRING_KEY_PREFIX = "redi2read:strings:";
	 
	 @PostMapping("/strings")
	 @ResponseStatus(HttpStatus.CREATED)
	 public Map.Entry<String, String> setString(@RequestBody Map.Entry<String, String> kvp) {
		 template.opsForValue().set(STRING_KEY_PREFIX + kvp.getKey(), kvp.getValue());
		 return kvp; 
	 }
	
}
