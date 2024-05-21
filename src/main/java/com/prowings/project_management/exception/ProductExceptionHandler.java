package com.prowings.project_management.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.prowings.project_management.model.ErrorResponse;


@ControllerAdvice
public class ProductExceptionHandler {
	
	@ExceptionHandler(InvalidProductDetailsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidProductDetailsException(WebRequest webRequest, Exception ex){
		
		System.out.println("Inside handleInvalidProductException() handler method ");
		ErrorResponse error = new ErrorResponse();
		error.setCause(ex.getMessage());
		error.setStatusCode(400);
		error.setDescription("The root cause of this is : "+ex.getMessage());
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        error.setTimestamp(now.format(formatter));

        String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(WebRequest webRequest, Exception ex){
		
		System.out.println("Inside handleProductNotFoundException() handler method ");
		ErrorResponse error = new ErrorResponse();
		error.setCause(ex.getMessage());
		error.setStatusCode(404);
		error.setDescription("The root cause of this is : "+ex.getMessage());
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        error.setTimestamp(now.format(formatter));
		
        String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ProductDeletionFailedException.class)
	public ResponseEntity<ErrorResponse> handleProductDeletionFailedException(WebRequest webRequest, Exception ex){
		
		System.out.println("Inside handleProductDeletionFailedException() handler method ");
		ErrorResponse error = new ErrorResponse();
		error.setCause(ex.getMessage());
		error.setStatusCode(409);
		error.setDescription("The root cause of this is : "+ex.getMessage());
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        error.setTimestamp(now.format(formatter));
		
		String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		
		
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(ProductUpdateFailedException.class)
	public ResponseEntity<ErrorResponse> handleProductUpdateFailedException(WebRequest webRequest, Exception ex){
		
		System.out.println("Inside handleProductDeletionFailedException() handler method ");
		ErrorResponse error = new ErrorResponse();
		error.setCause(ex.getMessage());
		error.setStatusCode(400);
		error.setDescription("The root cause of this is : "+ex.getMessage());
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		error.setTimestamp(now.format(formatter));
		
		String reqUrl = webRequest.getContextPath();
		System.out.println(reqUrl);
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	

}
