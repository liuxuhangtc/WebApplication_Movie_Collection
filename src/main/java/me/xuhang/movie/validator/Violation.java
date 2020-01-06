package me.xuhang.movie.validator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by xuhang on 2019/11/20.
 */
@Data
@AllArgsConstructor
public class Violation {

    private String message;

    private Object invalidValue;

    private Object invalidObject;

    public Violation() {
    	
    }
    
   public Violation(String message,Object invalidValue,Object invalidObject) {
    	this.message = message;
    	this.invalidValue= invalidValue;
    	this.invalidObject = invalidObject;
    }
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(Object invalidValue) {
		this.invalidValue = invalidValue;
	}

	public Object getInvalidObject() {
		return invalidObject;
	}

	public void setInvalidObject(Object invalidObject) {
		this.invalidObject = invalidObject;
	}
    
}
