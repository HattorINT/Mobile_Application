package com.example.mobileapp_m1_secure_development;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AfterLogin extends AppCompatActivity {
    private AccountViewModel accountViewModel;
    private TextView textViewResult;
    private FloatingActionButton refresh;
    private FloatingActionButton add;
    public static final int ADD_ACCOUNT_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reception);


        refresh = findViewById(R.id.button_refresh_account);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit();
            }
        });

        add = findViewById(R.id.button_add_account);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean connected = isnetwork();
                if (!connected){
                    Toast.makeText(AfterLogin.this, "Unable to reach the Internet !", Toast.LENGTH_SHORT).show();
                }
                else{
                Intent intent = new Intent(AfterLogin.this, AddAccountActivity.class);
                startActivityForResult(intent,ADD_ACCOUNT_REQUEST);}
                //accountViewModel.deleteAllAccounts();

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final AccountAdapter adapter = new AccountAdapter();
        recyclerView.setAdapter(adapter);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.getAllAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable List<Account> accounts) {
                adapter.setAccounts(accounts);

                //Toast.makeText(AfterLogin.this, "onChanged", Toast.LENGTH_SHORT).show();

            }
        });




    }



    public void Retrofit(){
        Resources resources = getResources();
        String url = String.format(resources.getString(R.string.URL_cache));
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Account>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AfterLogin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                accountViewModel.deleteAllAccounts();
                List<Account> accountList = response.body();

                for (Account account : accountList){
                    Account accounttemp = new Account(account.getId(), account.getAccountName(), account.getAmount(), account.getIban(), account.getCurrency());
                    accountViewModel.insert(accounttemp);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Toast.makeText(AfterLogin.this, "Unable to reach the Internet !", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ACCOUNT_REQUEST && resultCode == RESULT_OK) {

            String account = data.getStringExtra(AddAccountActivity.EXTRA_ACCOUNT);
            String iban = data.getStringExtra(AddAccountActivity.EXTRA_IBAN);
            String amount = data.getStringExtra(AddAccountActivity.EXTRA_AMOUNT);

            if(amount.matches("")){
                amount = "0.00";
            }
            String currency = data.getStringExtra(AddAccountActivity.EXTRA_CURRENCY);

            Account acc = new Account(account, Double.parseDouble(amount), iban,currency);
            accountViewModel.insert(acc);
            Toast.makeText(this, "Account saved", Toast.LENGTH_SHORT).show();






            Resources resources = getResources();
            String url = String.format(resources.getString(R.string.URL_cache));
            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<Account> call = jsonPlaceHolderApi.createPost(acc);
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(AfterLogin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(AfterLogin.this, "Unable to reach the Internet !", Toast.LENGTH_SHORT).show();
                }
            });

        }

        else {
            Toast.makeText(this, "Account not saved", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isnetwork(){
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;

            if (manager != null){
                networkInfo = manager.getActiveNetworkInfo();
            }
            return networkInfo != null && networkInfo.isConnected();
        } catch (Exception e){
            return false;
        }
    }




}