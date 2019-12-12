package com.github.zj.dreamly.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h2>UserApplication</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 10:42
 **/
@SpringBootApplication
@EnableFeignClients
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
