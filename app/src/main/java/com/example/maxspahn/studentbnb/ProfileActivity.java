package com.example.maxspahn.studentbnb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private TextView nameText;
    private TextView residenceCity;
    private TextView residenceName;
    private TextView myResidence;
    private ImageView profileImage;
    private ImageView roomImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile); // here, you can create a single layout with a listview

        listView = (ListView) findViewById(R.id.listView);

        String[] profileElements = {"Evaluations", "My Trips"};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profileElements);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = String.valueOf(adapterView.getItemIdAtPosition(i));
                Toast.makeText(ProfileActivity.this, option, Toast.LENGTH_SHORT).show();
            }
        });

        // Set the information of the views
        // We first find the views by id
        nameText = (TextView) findViewById(R.id.nameText);
        residenceCity = (TextView) findViewById(R.id.residenceCity);
        residenceName = (TextView) findViewById(R.id.residenceName);
        myResidence = (TextView) findViewById(R.id.myResidence);
        profileImage = (ImageView) findViewById(R.id.profileImage);
        roomImage = (ImageView) findViewById(R.id.roomImage);


        myResidence.setText("My Room");
        



    }

}
