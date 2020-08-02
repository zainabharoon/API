package com.example.API;


public class AccountNotFoundException extends Exception {
    private String accountId;

    public AccountNotFoundException(String accountId) {
        super(String.format("Account not found: %s", accountId));
    }
}