package org.felipe.springbootapp.features;

import org.felipe.springbootapp.features.account.AccountController;
import org.felipe.springbootapp.features.account.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

    @Autowired
    AccountController accountController;

    Long createdId;

    @Before
    public void before(){

    }

    @Test
    public void list(){
        List<Account> res = accountController.list();
        assertThat(res).isNotNull();
    }

    @Test
    public void create(){
        Account account = new Account("Fullname of user","username","user@email.com");
        Account result = accountController.create(account);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Fullname of user");
        assertThat(result.getUsername()).isEqualTo("username");
        assertThat(result.getEmail()).isEqualTo("user@email.com");
        createdId = result.getId();
    }

    @Test
    public void update(){
        Account savedAccount = accountController.findByUsername("username");
        Account account = new Account("Fullname of user 123","username","user@email.com");
        Account result = accountController.update(savedAccount.getId(),account);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Fullname of user 123");
        assertThat(result.getUsername()).isEqualTo("username");
        assertThat(result.getEmail()).isEqualTo("user@email.com");
    }

    @Test
    public void findByUsername(){
        Account result = accountController.findByUsername("username");
        assertThat(result).isNotNull();
    }

    @Test
    public void deleteById(){
        Account result = accountController.findByUsername("username");
        accountController.deleteById(result.getId());
        result = accountController.findByUsername("username");
        assertThat(result).isNull();
    }

    @After
    public void after(){

    }

}
