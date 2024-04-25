package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.common.error.ApiErrorResponse;
import eu.getsoftware.cleanarchitecture.common.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleApiException(UserNotFoundException ex) {
		
		ApiErrorResponse response =
				new ApiErrorResponse("error-0001",
						"No user found with ID " + ex.getId());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}