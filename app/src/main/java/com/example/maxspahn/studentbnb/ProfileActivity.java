package com.example.maxspahn.studentbnb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile); // here, you can create a single layout with a listview

        listView = (ListView) findViewById(R.id.listView);

        String[] profileElements = {"Evaluations", "My Trips", "My info"};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profileElements);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = String.valueOf(adapterView.getItemIdAtPosition(i));
                Toast.makeText(ProfileActivity.this, option, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
