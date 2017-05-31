package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private TextView nameText;
    private TextView residenceCity;
    private TextView residenceName;
    private TextView myResidence;
    private ImageView profileImage;
    private ImageView roomImage;
    private BottomNavigationView bottomNavigationView;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile); // here, you can create a single layout with a listview

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        listView = (ListView) findViewById(R.id.listView);

        String[] profileElements = {"Evaluations", "My Trips", "My Info"};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profileElements);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = ((TextView) view).getText().toString();

                switch (option) {
                    case "Evaluations":
                        Intent intent1 = new Intent(getApplicationContext(), EvaluationsActivity.class);
                        intent1.putExtra("user", user);
                        startActivity(intent1);
                        break;

                    case "My Trips":
                        Intent intent2 = new Intent(getApplicationContext(), TripsActivity.class);
                        intent2.putExtra("user", user);
                        startActivity(intent2);
                        break;

                    case "My Info":
                        Intent intent3 = new Intent(getApplicationContext(), InfoActivity.class);
                        intent3.putExtra("user", user);
                        startActivity(intent3);
                        break;
                }
            }
        });

        /*
        ***************************************Receiving and showing information*******************************************************
         */
        // We first find the views by id
        nameText = (TextView) findViewById(R.id.nameText);
        residenceCity = (TextView) findViewById(R.id.residenceCity);
        residenceName = (TextView) findViewById(R.id.residenceName);
        myResidence = (TextView) findViewById(R.id.myResidence);
        profileImage = (ImageView) findViewById(R.id.profileImage);
        roomImage = (ImageView) findViewById(R.id.roomImage);


        nameText.setText(user.getName() + user.getSurname());
        myResidence.setText("My Room");
        residenceName.setText(user.getResidence().getName());
        residenceCity.setText(user.getResidence().getCity());
        //TODO set the images retrieved from the user.


        /*
        ***************************************Bottom menu*******************************************************
         */
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        // call SearchRoom activity
                        break;
                    case R.id.action_trip:
                        // call Trip activity
                        break;
                    case R.id.action_profile:
                        // do nothing
                        break;
                }
                return true;
            }
        });


    }


}
