package com.example.mobileapp_m1_secure_development;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String accountName;
    private Double amount;
    private String iban;
    private String currency;


    public Account(int id,String accountName, Double amount, String iban, String currency) {
        this.id = id;
        this.accountName = accountName;
        this.amount = amount;
        this.iban = iban;
        this.currency = currency;
    }

    @Ignore
    public Account(String accountName, Double amount, String iban, String currency) {
        this.accountName = accountName;
        this.amount = amount;
        this.iban = iban;
        this.currency = currency;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public Double getAmount() {
        return amount;
    }

    public String getIban() {
        return iban;
    }

    public String getCurrency() {
        return currency;
    }

}


