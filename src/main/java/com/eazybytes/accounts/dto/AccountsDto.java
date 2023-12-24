package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
	private Long account_number;

	private String account_type;

	private String branch_address;
}
