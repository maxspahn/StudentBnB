package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Arturo on 06/06/2017.
 */

public class ChangeProfileActivity extends Activity {

    private User user;
    private EditText editName;
    private EditText editSurname;
    private EditText editUsername;
    private EditText editPassword;
    private EditText editTelephone;
    private EditText editEmail;
    private EditText editRoom;
    private Button buttonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_profile);

        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");

        editName = (EditText) findViewById(R.id.editName);
        editSurname = (EditText) findViewById(R.id.editSurname);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editTelephone = (EditText) findViewById(R.id.editTelephone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editRoom = (EditText) findViewById(R.id.editRoom);

        editName.setText(user.getName());
        editSurname.setText(user.getSurname());
        editUsername.setText(user.getUsername());
        editPassword.setText("*********");
        editTelephone.setText(user.getTelephone());
        editEmail.setText(user.getEmail());
        editRoom.setText(user.getRoomNumber());


    }

}
