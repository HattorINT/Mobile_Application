package com.example.mobileapp_m1_secure_development;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddAccountActivity extends AppCompatActivity {


    public static final String EXTRA_ACCOUNT =
            "com.codinginflow.architectureexample.EXTRA_ACCOUNT";
    public static final String EXTRA_IBAN =
            "com.codinginflow.architectureexample.EXTRA_IBAN";
    public static final String EXTRA_AMOUNT =
            "com.codinginflow.architectureexample.EXTRA_AMOUNT";
    public static final String EXTRA_CURRENCY =
            "com.codinginflow.architectureexample.EXTRA_CURRENCY";




    private EditText editTextAccount;
    private EditText editTextIban;
    private EditText editTextAmount;
    private EditText editTextCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_activity);
        editTextAccount = findViewById(R.id.edit_text_account);
        editTextIban = findViewById(R.id.edit_text_iban);
        editTextAmount = findViewById(R.id.edit_text_amount);
        editTextCurrency = findViewById(R.id.edit_text_currency);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Account");
    }
    private void saveAccount() {
        String account = editTextAccount.getText().toString();
        String iban = editTextIban.getText().toString();
        String amount = editTextAmount.getText().toString();
        String currency = editTextCurrency.getText().toString();

        if (account.trim().isEmpty() || iban.trim().isEmpty()) {
            Toast.makeText(this, "Please insert an account name and an IBAN", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_ACCOUNT, account);
        data.putExtra(EXTRA_IBAN, iban);
        data.putExtra(EXTRA_AMOUNT, amount);
        data.putExtra(EXTRA_CURRENCY, currency);


        setResult(RESULT_OK, data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_account_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_account:
                saveAccount();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}