package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Arturo on 31/05/2017.
 */

public class InfoActivity extends AppCompatActivity {
    User user;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        listView = (ListView) findViewById(R.id.listView);

        String[] infoElements = {"Name: " + user.getName() + user.getSurname(), "Username: " + user.getUsername(), "Telephone: " + user.getTelephone(), "Email: " + user.getEmail(), "City: " + user.getResidence().getCity(), "Residence: " + user.getResidence().getName(), "Room: " + user.getRoomNumber()};

        ListAdapter infoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoElements);
        listView.setAdapter(infoAdapter);

    }
}
