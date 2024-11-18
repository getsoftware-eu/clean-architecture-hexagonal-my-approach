package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.domain.model.domain.BusinessException;
import eu.getsoftware.cleanarchitecture.common.error.JsonErrorResponse;
import eu.getsoftware.cleanarchitecture.common.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<JsonErrorResponse> handleApiException(UserNotFoundException ex) {
		
		JsonErrorResponse response =
				new JsonErrorResponse("error-0001",
						"No user found with ID " + ex.getId());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<JsonErrorResponse> handleBusinessException(BusinessException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new JsonErrorResponse("BUSINESS_ERROR", ex.getMessage()));
	}

	/**
	 * MethodArgumentNotValidException возникает при валидации данных запроса с использованием @Valid (например, в DTO)
	 * и предоставляет подробную информацию об ошибках валидации
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
				errorResponse.put(error.getField(), error.getDefaultMessage())
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonErrorResponse> handleGenericException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new JsonErrorResponse("SERVER_ERROR", "An unexpected error occurred"));
	}
}