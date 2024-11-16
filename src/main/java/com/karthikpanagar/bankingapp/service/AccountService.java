package com.karthikpanagar.bankingapp.service;


import com.karthikpanagar.bankingapp.entity.Account;
import com.karthikpanagar.bankingapp.entity.AccountPool;
import com.karthikpanagar.bankingapp.repository.AccountPoolRepository;
import com.karthikpanagar.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountPoolRepository accountPoolRepository;

    public Account createAccount(Account account){
        Optional<AccountPool> optionalAccountPool  = accountPoolRepository.findFirstByOrderByIdAsc();
        if(optionalAccountPool.isPresent()){
            AccountPool accountPool = optionalAccountPool.get();
            account.setAccNumber(accountPool.getAccountNumber());
            accountPoolRepository.delete(accountPool);
        }
        else{
            Long maxAccNumber = accountRepository.findMaxAccNumber().orElse(0L); // Start from 1000
            account.setAccNumber(maxAccNumber + 1);
        }
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
        AccountPool accountPool = new AccountPool();
        accountPool.setAccountNumber(id);
        accountPoolRepository.save(accountPool);
    }

    public Account updateName(Long id, String newName){
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        account.setAccHolderName(newName);
        return accountRepository.save(account);
    }

    public Account getAccount(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
    }

    private Account makeTransaction(Long id, double amount, boolean isDeposit){
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        double updatedBalance;
        updatedBalance = isDeposit ?  account.getAccBalance() + amount : account.getAccBalance() - amount;
        if(updatedBalance < 0 )
            throw new RuntimeException("Insufficient Balance");
        account.setAccBalance(updatedBalance);
        return accountRepository.save(account);
    }

    public Account deposit(Long id, double depositAmount){
        return makeTransaction(id, depositAmount, true);
    }

    public Account withdraw(Long id, double withdrawAmt){
        return makeTransaction(id, withdrawAmt, false);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
