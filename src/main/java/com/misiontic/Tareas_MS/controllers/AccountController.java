package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.AccountAlreadyExistsException;
import com.misiontic.Tareas_MS.models.Account;
import com.misiontic.Tareas_MS.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController( AccountRepository accountRepository ){
        this.accountRepository = accountRepository;
    }

    @PostMapping("newAccount/{userId}")
    Account postAccount(@PathVariable String userId){
        Account newAccount = accountRepository.findById(userId).orElse(null);
        if(newAccount != null){
            throw new AccountAlreadyExistsException("Ya existe una cuenta con userid: "+newAccount.getUserId());
        }
        newAccount = new Account(userId);
        return accountRepository.save(newAccount);
    }

}
