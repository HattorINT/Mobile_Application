package com.example.mobileapp_m1_secure_development;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.sql.ClientInfoStatus;
import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository repository;
    private LiveData<List<Account>> allAccounts;
    //private LiveData<List<Account>> findLocal;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
        allAccounts = repository.getAllAccounts();
        //findLocal = repository.findLocal();
    }

        public void insert(Account account) {
            repository.insert(account);
        }
        public void update(Account account) {
            repository.update(account);
        }
        public void delete(Account account) {
            repository.delete(account);
        }
        public void deleteAllAccounts() {
            repository.deleteAllAccounts();
        }
        /*public LiveData<List<Account>> findLocal() {
            return findLocal;
        }*/
        public LiveData<List<Account>> getAllAccounts() {
            return allAccounts;
        }
    }

