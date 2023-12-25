package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Accounts extends BaseEntity {
	@Id
	private Long account_number;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	private String account_type;
	
	private String branch_address;
}
