package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @GetMapping("/accounts/{userId}")
    public Account getUserAccount(@PathVariable("userId") int userId) {
        return accountDao.getAccountByUserId(userId);
    }

    @PutMapping("/accounts/{accountId}")
    public void updateAccount(@PathVariable("accountId") int accountId, @RequestBody Account account) {
        accountDao.updateAccount(accountId, account);
    }


}
