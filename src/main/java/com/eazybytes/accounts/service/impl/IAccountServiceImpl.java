package com.eazybytes.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IAccountServiceImpl implements com.eazybytes.accounts.service.IAccountService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	
	@Override
	public void createAccount(CustomerDto customerDto) {
		var customer = CustomerMapper.mapToCustomer(customerDto, new Customer()); // converting from Dto object to entity
		
		var customerMobile = customerRepository.findByMobileNumber(customerDto.getMobile_number());
		
		if (customerMobile.isPresent())
			throw new CustomerAlreadyExistsException(
					"Customer already exists with mobile number: " + customerDto.getMobile_number());
		
		customer.setCreated_by("user123");
		customer.setCreated_at(LocalDateTime.now());
		var savedCustomer = customerRepository.save(customer); // this will fetch customer id after successfull save
		
		accountsRepository.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
        var newAccount = new Accounts();
        
        newAccount.setCustomer_id(customer.getCustomer_id());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCreated_by("user123");
        newAccount.setCreated_at(LocalDateTime.now());
        newAccount.setAccount_number(randomAccNumber);
        newAccount.setAccount_type(AccountsConstants.SAVINGS);
        newAccount.setBranch_address(AccountsConstants.ADDRESS);
        
        return newAccount;
    }
}
