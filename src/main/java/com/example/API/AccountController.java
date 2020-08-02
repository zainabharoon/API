package com.example.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts") // READ operation in CRUD
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{accountId}") // READ operation in CRUD
    public Optional<Account> getAllAccountById(@PathVariable(value="accountId") String accountId) {
        return accountRepository.findById(accountId);
    }

    @PostMapping("/accounts") // CREATE operation in CRUD
    public @Valid Account createEmployee(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{accountId}") // UPDATE operation for CRUD
    public @Valid Account updateAccount(@PathVariable(value="accountId") String accountId, @Valid @RequestBody Account account)
            throws AccountNotFoundException {
        Account acc = accountRepository.findById(accountId).orElseThrow(()->new AccountNotFoundException(accountId));
        acc.setAccountName(account.getAccountName());
        Account updAccount = accountRepository.save(acc);
        return updAccount;
    }

    @DeleteMapping("accounts/{accountId}") // DELETE operation for CRUD
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") String accountId) throws AccountNotFoundException {
        Account acc = accountRepository.findById(accountId).orElseThrow(()->new AccountNotFoundException(accountId));
        accountRepository.deleteById(accountId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}