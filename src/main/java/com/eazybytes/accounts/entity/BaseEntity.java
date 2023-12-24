package com.eazybytes.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseEntity {
	@Column(updatable = false)
	private LocalDateTime created_at;
	
	@Column(updatable = false)
	private String created_by;
	
	@Column(insertable = false)
	private LocalDateTime updated_at;
	
	@Column(insertable = false)
	private String updated_by;
}
