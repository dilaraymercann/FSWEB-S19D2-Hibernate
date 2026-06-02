package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    @Override
    public Account saveWithCustomer(Long customerId, Account account) {
        Customer customer = customerService.findById(customerId);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account updateWithCustomer(Long customerId, Account account) {
        Customer customer = customerService.findById(customerId);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account delete(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            accountRepository.delete(account);
        }
        return account;
    }

    @Override
    public Account find(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}