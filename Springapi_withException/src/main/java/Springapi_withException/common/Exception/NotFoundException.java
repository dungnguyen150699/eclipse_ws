package Springapi_withException.common.Exception;

public class NotFoundException extends RuntimeException{
	public NotFoundException(String message) {
		super(message);
	}
}
