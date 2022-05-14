package com.example.practice.service;

import com.example.practice.dto.AccountDTO;
import com.example.practice.dto.RegisterDTO;
import com.example.practice.entity.Account;
import com.example.practice.repository.AccountRepository;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void register(RegisterDTO newAccount) {
        String username = newAccount.getUsername();
        String password = newAccount.getPassword();
        String passwordcheck = newAccount.getPasswordcheck();
        if(username.equals("") || password.equals("") || passwordcheck.equals("")) return; //공란있음
        if(!password.equals(passwordcheck)) return; //비번확인실패

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        if(accountRepository.findByUsername(username) != null) return; //아이디 중복

        accountRepository.save(account);
    }

    public boolean login(AccountDTO accountDTO){
        String username = accountDTO.getUsername();
        String password = accountDTO.getPassword();
        if(username.equals("") || password.equals("")) return false; //공란있음

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        if(accountRepository.findByUsername(username).getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

}

