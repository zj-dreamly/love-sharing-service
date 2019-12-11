package com.github.zj.dreamly.user;

import com.zj.dreamly.common.auth.AuthAspect;
import com.zj.dreamly.common.util.JwtOperator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * <h2>UserApplication</h2>
 *
 * @author: 苍海之南
 * @since: 2019-12-11 10:42
 **/
@SpringBootApplication
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public JwtOperator jwtOperator(){
		return new JwtOperator();
	}

	@Bean
	public AuthAspect authAspect(JwtOperator jwtOperator){
		return new AuthAspect(jwtOperator);
	}
}
