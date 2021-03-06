package com.example.mobileapp_m1_secure_development;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account.class}, version =5 )
public abstract class AccountDatabase extends RoomDatabase {

    private static AccountDatabase instance;

    public abstract AccountDao acountDao();

    public static synchronized AccountDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AccountDatabase.class, "account_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private AccountDao accountDao;

        private PopulateDbAsyncTask(AccountDatabase db) {
            accountDao = db.acountDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            /*accountDao.insert(new Account(100,"Title 1", 20.00, "1", "dollars"));
            accountDao.insert(new Account(200,"Title 2", 20.00, "2","dollars"));
            accountDao.insert(new Account(300,"Title 3", 20.00, "3","dollars"));*/
            return null;
        }
    }
}
