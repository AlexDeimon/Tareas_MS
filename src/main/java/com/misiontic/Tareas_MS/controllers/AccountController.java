package com.misiontic.Tareas_MS.controllers;

import com.misiontic.Tareas_MS.exceptions.AccountAlreadyExistsException;
import com.misiontic.Tareas_MS.models.Account;
import com.misiontic.Tareas_MS.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController( AccountRepository accountRepository ){
        this.accountRepository = accountRepository;
    }

    @PostMapping("newAccount")
    Account postAccount(@RequestBody Account account){
        Account newAccount = accountRepository.findById(account.getUserId()).orElse(null);
        if(newAccount != null){
            throw new AccountAlreadyExistsException("Ya existe una cuenta con userid: "+newAccount.getUserId());
        }

        return accountRepository.save(account);
    }

}
