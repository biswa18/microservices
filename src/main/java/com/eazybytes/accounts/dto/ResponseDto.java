package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
		name = "Response",
		description ="Schema to hold common response object"
)
public class ResponseDto {
	private String statusCode;
	
	private String statusMessage;
	
	public ResponseDto(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
