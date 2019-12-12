package com.github.zj.dreamly.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h2>ContentApplication</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 10:42
 **/
@EnableFeignClients
@SpringBootApplication
public class ContentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContentApplication.class, args);
	}
}
