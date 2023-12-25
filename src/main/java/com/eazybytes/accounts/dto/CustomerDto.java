package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
		name = "Customer",
		description ="Schema to hold Customer along with Accounts info"
)
public class CustomerDto {
	private String name;

	private String email;

	private String mobile_number;
	
	private AccountsDto accounts;
}
