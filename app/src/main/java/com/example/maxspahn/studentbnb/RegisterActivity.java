package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by maxspahn on 06/06/17.
 */

public class RegisterActivity extends Activity {

    public Button register;
    public Button discard;
    public EditText username;
    public EditText password1;
    public EditText password2;
    public EditText surname;
    public EditText email;
    public EditText telephone;
    public EditText name;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.register);
        discard = (Button) findViewById(R.id.discard);

        username = (EditText) findViewById(R.id.username);
        surname = (EditText) findViewById(R.id.surname);
        email = (EditText) findViewById(R.id.email);
        telephone = (EditText) findViewById(R.id.telephone);
        name = (EditText) findViewById(R.id.name);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.register:
                        registerUser();
                        break;

                }
            }
        });

        discard = (Button) findViewById(R.id.discard);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.discard:
                        freeFields();
                        break;
                }
            }
        });
    }

    public void registerUser(){
        Log.i("CREATION", "hallo registere");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        String usernameT = username.getText().toString();
        String nameT = name.getText().toString();
        String telT = telephone.getText().toString();
        String emailT = email.getText().toString();
        String surnameT = surname.getText().toString();
        String passwordT = password1.getText().toString();
        String passwordT2 = password2.getText().toString();

        if(passwordT.equals(passwordT2)) {
            User tempUser = new User(nameT, surnameT, usernameT, passwordT, telT, emailT);
            DatabaseReference ref = database.getReference(tempUser.getUsername());
            ref.setValue(tempUser);
            freeFields();
        }
        else{
            Toast toast = null;
            toast.makeText(getApplicationContext(), "The two two passwords are not identical", Toast.LENGTH_SHORT);
            password2.setText("");
        }

    }


    void freeFields() {
        username.setText("");
        surname.setText("");
        password1.setText("");
        password2.setText("");
        email.setText("");
        telephone.setText("");
        name.setText("");



    }

/*

    public void uploadPicture(){
        // Create a storage reference from our app
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

        // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
    }
*/

}
