package org.felipe.springbootapp.features.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("")
    public List<Account> list() {
        return accountService.list();
    }

    @PostMapping("")
    public Account create(Account account) {
        return accountService.create(account);
    }

    @PutMapping("/{id}")
    public Account update(@RequestParam("id") Long id, Account account) {
        return accountService.update(id, account);
    }

    @GetMapping("/{username")
    public Account findByUsername(@RequestParam("username") String username) {
        return accountService.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam("id") Long id) {
        accountService.deleteById(id);
    }
}
