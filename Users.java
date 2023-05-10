package com.example.test123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test123.datas.MyDbHandler;
import com.example.test123.models.Contacts;

import java.util.ArrayList;

public class Users extends AppCompatActivity {
    ListView listview;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        listview = findViewById(R.id.userslistview);
        autoCompleteTextView =  findViewById(R.id.autoComplete);


        ArrayList<String> names = new MyDbHandler(this).getAllNames();

        ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        autoCompleteTextView.setAdapter(searchAdapter);
        autoCompleteTextView.setThreshold(1);

        ArrayList<Contacts> users = (ArrayList<Contacts>) new MyDbHandler(this).getAllContacts();
        Adopter adopter = new Adopter(this, users);
        listview.setAdapter(adopter);

    }
}