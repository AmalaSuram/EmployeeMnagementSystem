package com.hcl.ems.exception;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.ems.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * Handle the not valid field errors along with validation messages and get the
	 * list of multiple field errors.
	 * 
	 * @author Amala.S
	 * @since 18-08-2020
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors for field valid
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorDto> salaryLimitException(EmployeeNotFoundException ex) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorStatusMessage(ex.getMessage());
		errorDto.setErrorStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}



}
