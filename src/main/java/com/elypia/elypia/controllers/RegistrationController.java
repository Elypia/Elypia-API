package com.elypia.elypia.controllers;

import com.elypia.elypia.Elypia;
import com.elypia.elypia.entities.Account;
import com.elypia.elypia.entities.Available;
import com.elypia.elypia.entities.Verification;
import com.elypia.elypia.requests.AccountData;
import com.elypia.elypia.responses.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegistrationController {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    @RequestMapping(value = "/api/account", method = POST)
    public User createAccount(@RequestBody AccountData data) {
        validator.validate(data);

        Verification verification = new Verification();
        verification.setConfirmationToken(Verification.generate());

        Account account = new Account();
        account.setId(new Random().nextInt(Integer.MAX_VALUE));
        account.setEmail(new String[] {data.getEmail()});
        account.setUsername(data.getUsername());
        account.setHash("fiowhy89fh289fby298ryb8923rbyc298y3rbc9823ybrc9823ybvrc9");
        account.setVerification(verification);
        account.setAdmin(false);
        Elypia.store.save(account);

        return new User(account);
    }

    @RequestMapping(value = "/api/verify/{id}", method = GET)
    public User verifyAccount(@PathVariable String id) {
        List<Account> accounts = Elypia.store.find(Account.class).field("verification.token").equal(id).asList();

        // If no account has a confirmation value with this value, return nothing.
        if (accounts.size() == 0)
            return null;

        // If multiple accounts have this confirmation token, reset each.
        if (accounts.size() > 1) {

        }

        Account account = accounts.get(0);

        Verification verification = account.getVerification();
        verification.setVerified(true);
        verification.setConfirmationToken(null);

        Elypia.store.save(account);
        return new User(account);
    }
}
