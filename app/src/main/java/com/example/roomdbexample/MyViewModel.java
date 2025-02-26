package com.example.roomdbexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// AndroidViewModel class is a subclass of ViewModel
// and similar to them, they are designed to store and
// manage UI-related data are responsible to
// prepare & provide data from UI and automatically
// allow data to survive configuration change.
public class MyViewModel extends AndroidViewModel {

    // if you need to use context inside your ViewModel
    // you should use AndroidViewModel because
    // it contains the application context.
    private Repository myRepository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }

    public void addContact(Contacts contact){
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        myRepository.deleteContact(contact);
    }
}
