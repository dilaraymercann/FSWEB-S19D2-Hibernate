package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerResponse> getAll() {
        return customerService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return toResponse(customerService.findById(id));
    }

    @PostMapping
    public CustomerResponse create(@RequestBody Customer customer) {
        return toResponse(customerService.save(customer));
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer) {
        return toResponse(customerService.update(id, customer));
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        customerService.delete(id);
        return toResponse(customer);
    }

    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getEmail(), c.getSalary());
    }
}