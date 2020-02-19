/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.api.controllers;

import org.elypia.api.entities.*;
import org.elypia.api.forms.*;
import org.elypia.api.repositories.*;
import org.elypia.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

/**
 * This controller is for managing the account of a user, not to be
 * confused with the {@link UserController}.
 *
 * @author seth@elypia.org (Syed Shah)
 */
@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    /** Access users via the database. */
    private AccountService accountService;
    private AccountRepository accountRepo;
    private VerificationRepository verifyRepo;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepo, VerificationRepository verifyRepo) {
        this.accountService = accountService;
        this.accountRepo = accountRepo;
        this.verifyRepo = verifyRepo;
    }

    @GetMapping("/exists")
    @ResponseStatus
    public ResponseEntity userExists(@RequestParam String email) {
        if (accountService.emailExists(email))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>("No accounts exist with that email address.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public Account register(@RequestBody @Valid RegistrationForm form) {
        return accountService.registerNewAccount(form.getEmail(), form.getPassword());
    }

    @PostMapping("/authenticate")
    public Principal authenticate(Principal account) {
        return account;
    }

    @PostMapping("/reset")
    public Object passwordReset(@RequestBody @Valid PasswordResetForm form) {
        return accountService.resetPassword(form.getToken(), form.getPassword());
    }

    /**
     * Used when a user has forgotten their password, emails them a reset link.
     * The reset link should then call {@link #passwordReset(PasswordResetForm)}.
     *
     * @param email The email of the user that forgot their password.
     * @return A response entity representing a response status.
     */
    @PostMapping("/forgot")
    public ResponseEntity forgottenPassword(@RequestBody @Valid String email) {
        return null;
    }

    /** TODO: This doesn't belong in the controller class, it should be in a service.
     * Verify a user account to that they are able to login.
     * Failure to do so may result in account deletion.

     * @param token The verification token sent to the user.
     * @return The HTTP response for the user.
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verify(@PathVariable("token") String token) {
        VerificationToken verificationToken = verifyRepo.findByToken(token);

        if (verificationToken == null)
            return new ResponseEntity<>("Verification token does not exist.", HttpStatus.NOT_FOUND);

        Account account = verificationToken.getAccount();
        verifyRepo.delete(verificationToken);

        if (account.isVerified())
            return new ResponseEntity<>("You have already verified your account.", HttpStatus.BAD_REQUEST);

        if (new Date().after(verificationToken.getExpiry()))
            return new ResponseEntity<>("This verification token has expired, please generate another and try again.", HttpStatus.NOT_FOUND);

        account.setVerified(true);
        accountRepo.save(account);
        return new ResponseEntity<>("You have succesfully verified your account.", HttpStatus.OK);
    }
}
