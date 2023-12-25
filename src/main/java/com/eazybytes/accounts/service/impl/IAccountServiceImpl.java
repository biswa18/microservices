package com.eazybytes.accounts.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IAccountServiceImpl implements IAccountService {
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	
	@Override
	public void createAccount(CustomerDto customerDto) {
		var customer = CustomerMapper.mapToCustomer(customerDto, new Customer()); // converting from Dto object to entity
		
		var customerMobile = customerRepository.findByMobileNumber(customerDto.getMobile_number());
		
		if (customerMobile.isPresent())
			throw new CustomerAlreadyExistsException(
					"Customer already exists with mobile number: " + customerDto.getMobile_number());
		
		var savedCustomer = customerRepository.save(customer); // this will fetch customer id after successfull save
		
		accountsRepository.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
        var newAccount = new Accounts();
        
        newAccount.setCustomerId(customer.getCustomer_id());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccount_number(randomAccNumber);
        newAccount.setAccount_type(AccountsConstants.SAVINGS);
        newAccount.setBranch_address(AccountsConstants.ADDRESS);
        
        return newAccount;
    }

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		var customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
		var accounts = accountsRepository.findByCustomerId(customer.getCustomer_id()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomer_id().toString()));
		
		var customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccounts(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		
		return customerDto;
    }

	@Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        var accountsDto = customerDto.getAccounts();
        
        if(accountsDto !=null )
        {
            var accounts = accountsRepository.findById(accountsDto.getAccount_number()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccount_number().toString())
            );
            
            
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            var customerId = accounts.getCustomerId();
            var customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }
	
	@Override
	public boolean deleteAccount(String mobileNumber) {
		var customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		accountsRepository.deleteByCustomerId(customer.getCustomer_id());
		customerRepository.deleteById(customer.getCustomer_id());

		return true;
	}
}
