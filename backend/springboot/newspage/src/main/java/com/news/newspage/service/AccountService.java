package com.news.newspage.service;

import com.news.newspage.dto.AccountDTO;
import com.news.newspage.dto.RegisterDTO;
import com.news.newspage.entity.Account;
import com.news.newspage.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class AccountService{
    @Autowired
    AccountRepository accountRepository;
    public String register(RegisterDTO newAccount) {
        String username = newAccount.getUsername();
        String password = newAccount.getPassword();
        String passwordcheck = newAccount.getPasswordcheck();
        if(username.equals("") || password.equals("") || passwordcheck.equals("")) return "Please enter all";
        if(!password.equals(passwordcheck)) return "Please check your password again";

        Optional<Account> optionalrepository = Optional.ofNullable(accountRepository.findByUsername(username));
        if(!optionalrepository.isPresent()) return "The ID that already exists";

        optionalrepository.ifPresent(account->{
            account.setUsername(username);
            account.setPassword(password);
            accountRepository.save(account);
        });
        return "";
    }

    public String login(AccountDTO accountDTO){
        String username = accountDTO.getUsername();
        String password = accountDTO.getPassword();
        if(username.equals("") || password.equals("")) {
            return "Please enter all";
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return "";
    }

}




