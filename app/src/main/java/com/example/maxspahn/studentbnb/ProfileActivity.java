package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Bitmap;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

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
    private Button buttonChange;
    private Button buttonRoom;
    private static Bitmap Image = null;
    private static Bitmap Image2 = null;
    private static final int GALLERY = 1;
    private static final int GALLERY2 = 2;
    private static Bitmap rotateImage = null;
    private static Bitmap rotateImage2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        user = loadUserData();

        listView = (ListView) findViewById(R.id.listView);

        String[] profileElements = {"My Trips", "My Info", "Room Availability"};

        ListAdapter profileAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profileElements);
        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String option = ((TextView) view).getText().toString();

                switch (option) {
                    case "My Trips":
                        Intent intent2 = new Intent(getApplicationContext(), TripsActivity.class);
                        intent2.putExtra("user", (Serializable) user);
                        startActivity(intent2);
                        break;

                    case "My Info":
                        Intent intent3 = new Intent(getApplicationContext(), InfoActivity.class);
                        intent3.putExtra("user", (Serializable) user);
                        startActivity(intent3);
                        break;

                    case "Room Availability":
                        Intent intent4 = new Intent(getApplicationContext(), AvailabilityActivity.class);
                        intent4.putExtra("user", (Serializable) user);
                        startActivity(intent4);
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
        buttonChange = (Button) findViewById(R.id.buttonChange);
        buttonRoom = (Button) findViewById(R.id.buttonRoom);


        nameText.setText(user.getName() + " " + user.getSurname());
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
                        launchSearchRoomActivity();
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

        buttonChange.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileImage.setImageBitmap(null);
                if (Image != null)
                    Image.recycle();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
            }
        });

        buttonRoom.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));
        buttonRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomImage.setImageBitmap(null);
                if (Image != null)
                    Image.recycle();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY2);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY && resultCode != 0) {
            Uri mImageUri = data.getData();
            try {
                Image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                if (getOrientation(getApplicationContext(), mImageUri) != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(getOrientation(getApplicationContext(), mImageUri));
                    if (rotateImage != null)
                        rotateImage.recycle();
                    rotateImage = Bitmap.createBitmap(Image, 0, 0, Image.getWidth(), Image.getHeight(), matrix,true);
                    profileImage.setImageBitmap(rotateImage);
                } else
                    profileImage.setImageBitmap(Image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == GALLERY2 && resultCode != 0) {
            Uri mImageUri = data.getData();
            try {
                Image2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                if (getOrientation(getApplicationContext(), mImageUri) != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(getOrientation(getApplicationContext(), mImageUri));
                    if (rotateImage2 != null)
                        rotateImage2.recycle();
                    rotateImage2 = Bitmap.createBitmap(Image2, 0, 0, Image2.getWidth(), Image2.getHeight(), matrix,true);
                    roomImage.setImageBitmap(rotateImage2);
                } else
                    roomImage.setImageBitmap(Image2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    private User loadUserData() {
        User newUser1 = new User("Pedro", "Leon", "pleonpita", "pedron", "06959599143", "pleonpita@gmail.com");
        User newUser2 = new User("Arturo", "Garrido", "arturogc", "arthur", "0782683879", "arturo.garrido.contreras@gmail.com");

        Residence ecp = new Residence("Ecole Centrale", "Paris", "5 Avenue Sully Prudhomme, 92290 Châtenay-Malabry");
        newUser2.setResidence(ecp);
        ecp.addUser(newUser2);
        newUser2.setRoomNumber("E221");
        Residence sp = new Residence("San Pablo", "Madrid", "Calle de Isaac Peral, 58, 28040 Madrid, Espagne");
        newUser1.setResidence(sp);
        sp.addUser(newUser1);
        newUser1.setRoomNumber("B101");


        Trip trip1 = new Trip(new Date(2016, 06, 13), new Date(2016, 06, 20), newUser1, newUser2);
        trip1.setEvaluation(Evaluation.GOOD);
        trip1.confirmTrip();

        Trip trip2 = new Trip(new Date(2017, 02, 5), new Date(2017, 02, 8), newUser2, newUser1);
        trip2.setEvaluation(Evaluation.EXCELLENT);
        trip2.confirmTrip();

        Trip trip3 = new Trip(new Date(2017, 04, 10), new Date(2017, 04, 15), newUser2, newUser1);
        trip3.confirmTrip();

        return newUser2;

    }

    private void launchSearchRoomActivity(){
        Intent intent = new Intent(this, SearchRoomActivity.class);
        startActivity(intent);
    }
}
