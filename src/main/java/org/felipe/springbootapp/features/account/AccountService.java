package org.felipe.springbootapp.features.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    public Account findById(Long id) {
        return repository.find(id);
    }

    public Account update(Long id,Account account) {
        return repository.update(id, account);
    }

    public void deleteById(Long id) {
        repository.delete(id);
    }

    public Account create(Account account) {
        return repository.create(account);
    }

    public List<Account> list() {
        return repository.list();
    }

    public Account findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
