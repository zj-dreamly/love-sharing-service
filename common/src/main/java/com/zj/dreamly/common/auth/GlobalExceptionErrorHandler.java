package com.zj.dreamly.common.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 苍海之南
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {
	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<ErrorBody> error(SecurityException e) {
		log.warn("发生SecurityException异常", e);
		return new ResponseEntity<>(
			ErrorBody.builder()
				.body(e.getMessage())
				.status(HttpStatus.UNAUTHORIZED.value())
				.build(),
			HttpStatus.UNAUTHORIZED
		);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorBody> business(RuntimeException e) {
		log.warn("请求异常", e);
		return new ResponseEntity<>(
			ErrorBody.builder()
				.body(e.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build(),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorBody {
	private String body;
	private int status;
}
