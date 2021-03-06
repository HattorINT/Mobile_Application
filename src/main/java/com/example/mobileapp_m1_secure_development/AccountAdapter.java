package com.example.mobileapp_m1_secure_development;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    private List<Account> accounts = new ArrayList<>();
    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item, parent, false);
        return new AccountHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        Account currentAccount = accounts.get(position);
        holder.textViewId.setText(String.valueOf(currentAccount.getId()));
        holder.textViewIban.setText(String.valueOf(currentAccount.getIban()));
        holder.textViewAccount.setText(String.valueOf(currentAccount.getAccountName()));
        holder.textViewAmount.setText(String.valueOf(currentAccount.getAmount()));
        holder.textViewCurrency.setText(String.valueOf(currentAccount.getCurrency()));
    }
    @Override
    public int getItemCount() {
        return accounts.size();
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }
    class AccountHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewIban;
        private TextView textViewAccount;
        private TextView textViewAmount;
        private TextView textViewCurrency;

        public AccountHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewIban = itemView.findViewById(R.id.text_view_iban);
            textViewAmount = itemView.findViewById(R.id.text_view_amount);
            textViewAccount = itemView.findViewById(R.id.text_view_account);
            textViewCurrency = itemView.findViewById(R.id.text_view_currency);
        }
    }
}
