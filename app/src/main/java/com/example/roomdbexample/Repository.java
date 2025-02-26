package com.example.roomdbexample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    // The Available Data Sources:
    // - Room Database

    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;
    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        // used for background database operation
        executor = Executors.newSingleThreadExecutor();
        // used for updating the UI
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){
        // Runnable: Executing Tasks on separate thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });

    }

    public void deleteContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });

    }

    // we can't return data from background thread because the run method is void
    // instead we use LiveData to observe the data
    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}
