package com.example.maxspahn.studentbnb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import android.util.Log;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProfileActivity extends AppCompatActivity {

    private ListView listView;
    private TextView nameText;
    private TextView residenceCity;
    private TextView residenceName;
    private TextView myResidence;
    private ImageView profileImage;
    private ImageView roomImage;
    public User user;
    private BottomNavigationView bottomNavigationView;
    private Button buttonChange;
    private Button buttonRoom;
    private static Bitmap Image = null;
    private static Bitmap Image2 = null;
    private static final int GALLERY = 1;
    private static final int GALLERY2 = 2;
    private static Bitmap rotateImage = null;
    private static Bitmap rotateImage2 = null;
    private StorageReference mStorageRef;
    public String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        username = getIntent().getStringExtra("username");

        getUser(username);




    }

    public void getInfo(final User userTemp){

        Log.d("CREATION", "Usertemp name : " + userTemp.getName());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        DatabaseReference ref = database.getReference(userTemp.getUsername());
        ref.setValue(userTemp);



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
                        intent2.putExtra("username", username);
                        startActivity(intent2);
                        break;

                    case "My Info":
                        Intent intent3 = new Intent(getApplicationContext(), InfoActivity.class);
                        intent3.putExtra("username", username);
                        startActivity(intent3);
                        break;

                    case "Room Availability":
                        Intent intent4 = new Intent(getApplicationContext(), AvailabilityActivity.class);
                        intent4.putExtra("username", username);
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


        nameText.setText(userTemp.getName() + " " + userTemp.getSurname());
        myResidence.setText("My Room");
        residenceName.setText(userTemp.getResidence().getName());
        residenceCity.setText(userTemp.getResidence().getCity());
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
        buttonChange.setTextColor(Color.WHITE);
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
        buttonRoom.setTextColor(Color.WHITE);
        buttonRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomImage.setImageBitmap(null);
                if (Image2 != null)
                    Image2.recycle();
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

    public void getUser(String username){

        Log.d("CREATION", "in get user in profil, ref : ");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Log.d("CREATION", "in get user in profil, ref " + username);

        //Get the user.
        DatabaseReference ref  = database.getReference(username);

        Log.d("CREATION", "in get user in profil, ref : " + ref.toString());

        // Read from the database and check if userName fits to password.
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user = dataSnapshot.getValue(User.class);
                Log.d("CREATION", "username : " + user.getEmail());
                getInfo(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

    }


    private void launchSearchRoomActivity(){
        Intent intent = new Intent(this, SearchRoomActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
