package com.example.demo.common.error;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.ResponseBodyDto;
import com.example.demo.common.enums.ResponseCodeEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {
	/**
	 * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleServerError(Exception ex, WebRequest request) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.E_0001);
		dtoResult.setMessage("Error unknown");
		System.out.println(ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleException1(Exception ex, WebRequest request) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_404);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> errorMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_400);
		dtoResult.setMessage(errors.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(BadRequestException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleBadRequestException(Exception ex,
                                                                      WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_400);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(WhitelabelErrorException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleWhitelabelErrorException(Exception ex,
                                                                           WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_404);
		dtoResult.setMessage("Page not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleNotFoundException(Exception ex) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_404);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleIllegalArgumentException(Exception ex) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_404);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(dtoResult);
	}


	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleError405(HttpServletRequest request,
                                                           Exception e) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_405);
		dtoResult.setMessage(request.getMethod() + " " + request.getRequestURI() + " is not allowed!");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).headers(headers).body(dtoResult);
	}
	
//	@ExceptionHandler(HttpMessageNotReadableException.class)
//	public @ResponseBody ResponseEntity<ResponseBodyDto<Object>> handleMismatchedInputException(Exception ex) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
//		dtoResult.setCode(ResponseCodeEnum.R_400);
//		dtoResult.setMessage("sai kiểu dữ liệu trong request body");
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(dtoResult);
//	}

	// duplicate unique field
	@ExceptionHandler(DataIntegrityViolationException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleDataIntegrityViolationException(Exception ex,
                                                                                  WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_400);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(dtoResult);
	}

	@ExceptionHandler(ForbiddenException.class)
	public @ResponseBody
    ResponseEntity<ResponseBodyDto<Object>> handleForbiddenException(Exception ex) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_403);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(headers).body(dtoResult);
	}

	// Lỗi thiếu request param
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public @ResponseBody
	ResponseEntity<ResponseBodyDto<Object>> handleMissingServletRequestParameterException(Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBodyDto<Object> dtoResult = new ResponseBodyDto<>();
		dtoResult.setCode(ResponseCodeEnum.R_400);
		dtoResult.setMessage(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(dtoResult);
	}
}
