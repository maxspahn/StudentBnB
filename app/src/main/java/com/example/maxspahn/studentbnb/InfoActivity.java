package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Arturo on 31/05/2017.
 */

public class InfoActivity extends AppCompatActivity {
    User user;
    ListView listView;
    Button buttonChange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        listView = (ListView) findViewById(R.id.listView);

        String[] infoElements = {"Name: " + user.getName() + user.getSurname(), "Username: " + user.getUsername(), "Telephone: " + user.getTelephone(), "Email: " + user.getEmail(), "City: " + user.getResidence().getCity(), "Residence: " + user.getResidence().getName(), "Room: " + user.getRoomNumber()};

        ListAdapter infoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoElements);
        listView.setAdapter(infoAdapter);

        buttonChange = (Button) findViewById(R.id.buttonChange);
        buttonChange.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));
        buttonChange.setTextColor(Color.WHITE);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
}
