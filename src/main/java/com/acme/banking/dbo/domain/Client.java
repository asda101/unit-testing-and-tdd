package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be a positive integer or 0.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(this.accounts);
    }

    public void addAccount(Account newAccount) {
        if (newAccount == null) throw new IllegalArgumentException("Новый счет не должен быть null.");
        if (newAccount.getClient().id != this.id) throw new IllegalArgumentException("Новый счет не принадлежит этому клиенту.");
        if (this.accounts.contains(newAccount)) throw new IllegalArgumentException("Счет уже существует для этого клиента.");

        accounts.add(newAccount);
    }
}