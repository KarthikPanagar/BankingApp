package com.karthikpanagar.bankingapp.controller;

import com.karthikpanagar.bankingapp.entity.Account;
import com.karthikpanagar.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bankingapp")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id){
       return accountService.getAccount(id);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "Deleted user with ID: " + id;
    }

    @PutMapping("/{id}")
    public Account updateName(@PathVariable Long id,@RequestBody String newName){
        return accountService.updateName(id, newName);
    }

    @PostMapping("/{id}/{operation}")
    public Account withdraw(@PathVariable Long id, @PathVariable String operation, @RequestBody Map<String, Double> request) {
        if ("deposit".equalsIgnoreCase(operation)) {
            return accountService.deposit(id, request.get("amount"));
        } else if ("withdraw".equalsIgnoreCase(operation)) {
            return accountService.withdraw(id, request.get("amount"));
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
}
