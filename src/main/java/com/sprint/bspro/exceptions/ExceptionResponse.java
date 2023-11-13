package com.sprint.bspro.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {
	private String error;
	private String source;
	public ExceptionResponse() {
		super();
	}
	public ExceptionResponse(String error, String source) {
		super();
		this.error = error;
		this.source = source;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
