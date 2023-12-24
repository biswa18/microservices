package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class ResponseDto {
	private String statusCode;
	
	private String statusMessage;
	
	public ResponseDto(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
