package com.example.roomdbexample;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbexample.databinding.ContactItemLayoutBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_item_layout,
                parent,
                false
        );
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contact = contacts.get(position);
        holder.binding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        if (contacts != null)
            return contacts.size();
        else
            return 0;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        // Notify the adapter that the data has changed
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactItemLayoutBinding binding;

        public ContactViewHolder(ContactItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
