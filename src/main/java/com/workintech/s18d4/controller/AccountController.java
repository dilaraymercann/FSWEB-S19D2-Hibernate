package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<AccountResponse> getAll() {
        return accountService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return toResponse(accountService.find(id)); // findById → find
    }

    @PostMapping("/{customerId}")
    public AccountResponse create(@PathVariable Long customerId,
                                  @RequestBody Account account) {
        Customer customer = customerService.find(customerId); // customerService inject et
        account.setCustomer(customer);
        return toResponse(accountService.save(account)); // saveWithCustomer → save
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable Long customerId,
                                  @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        return toResponse(accountService.save(account)); // updateWithCustomer → save
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id) {
        Account account = accountService.find(id); // findById → find
        return toResponse(accountService.delete(id)); // ayrıca delete Account döndürüyor
    }

    private AccountResponse toResponse(Account a) {
        Long customerId = a.getCustomer() == null ? null : a.getCustomer().getId();
        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(), customerId);
    }
}