package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
		name = "Account",
		description ="Schema to hold Accounts info"
)
public class AccountsDto {
	private Long account_number;

	private String account_type;

	private String branch_address;
}
