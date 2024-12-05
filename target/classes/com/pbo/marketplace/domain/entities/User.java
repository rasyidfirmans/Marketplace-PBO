package com.pbo.marketplace.domain.entities;

import com.pbo.marketplace.domain.interfaces.UserInterface;

public class User implements UserInterface {
    private String email;
    private String password;
    private String name;
    private long balance;

    User(String email, String password, String name, long balance) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getBalance() {
        return balance;
    }
}
