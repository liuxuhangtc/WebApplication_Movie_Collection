package me.xuhang.movie.rest;

import lombok.Data;

/**
 * Created by xuhang on 2019/11/20.
 * Define a standard JSON return format for client
 */
@Data
public class RestData {

    //response status
    private int success = 0; // 0=fail
    //return code
    private String code;
    //if some error occurs, it will set some reasons to comment
    private String comment;
    //data field
    private Object data;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}
