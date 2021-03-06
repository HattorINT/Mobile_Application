package com.example.mobileapp_m1_secure_development;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private AccountViewModel accountViewModel;
    private TextView textViewResult;

    private TextView authStatusTv;
    private Button authBtn;


    private Executor executor;
    private BiometricPrompt biometricprompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        authStatusTv = findViewById(R.id.authStatusTv);
        authBtn = findViewById(R.id.authBtn);

        executor = ContextCompat.getMainExecutor(this);
        biometricprompt= new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {





            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                authStatusTv.setText("Authentication error ! ");
                Toast.makeText(MainActivity.this, "Authentication error !", Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                authStatusTv.setText("Authentication succeed !");
                Toast.makeText(MainActivity.this, "Authentication succeed !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                startActivity(intent);

            }





            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authStatusTv.setText("Authentication Failed !");
                Toast.makeText(MainActivity.this, "Authentication Failed !", Toast.LENGTH_SHORT).show();
            }
        });




        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint authentication")
                .setDeviceCredentialAllowed(true)
                .build();

        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricprompt.authenticate(promptInfo);
            }
        });

    }








}