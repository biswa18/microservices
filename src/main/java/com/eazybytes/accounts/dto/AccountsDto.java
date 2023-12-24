package com.eazybytes.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
	private Long customer_id;

	private String account_type;

	private String branch_address;
}
