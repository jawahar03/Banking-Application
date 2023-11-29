package com.bankapplication.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bankapplication.config.ResponseStructure;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> accountNotFound(AccountNotFound ex){
		ResponseStructure<String> str = new ResponseStructure<>();
		str.setData("Account Not Found");
		str.setMsg(ex.getMsg());
		str.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(str,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressNotFound(AddressNotFound ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Address not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bankNotFound(BankNotFound ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Bank not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.NOT_FOUND);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> branchNotFound(BranchNotFound ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Branch not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> incorrectPassword(IncorrectPassword ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Incorrect Password");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.FORBIDDEN);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> insufficientAmount(InsufficientAmount ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Insufficient amount");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidAccountNumber(InvalidAccountNumber ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Invalid Account Number");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidAccountType(InvalidAccountType ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Invalid Account Type");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidAmount(InvalidAmount ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Bank not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.BAD_REQUEST);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> managerNotFound(ManagerNotFound ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("Manager not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.NOT_FOUND);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userAlreadyPresent(UserAlreadyPresent ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("User already present in this branch");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.IM_USED.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.IM_USED);
	}
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException ex)
	{
		ResponseStructure<String> res = new ResponseStructure<>();
		res.setData("User not found");
		res.setMsg(ex.getMsg());
		res.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(res,HttpStatus.NOT_FOUND);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> list =ex.getAllErrors();
		HashMap<String , String> map = new HashMap<>();
		
		for (ObjectError objectError : list) {
			String msg = objectError.getDefaultMessage();
			String fieldName = ((FieldError)objectError).getField();
			map.put(fieldName , msg);
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
}
