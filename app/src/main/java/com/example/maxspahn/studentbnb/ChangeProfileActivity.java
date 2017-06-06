package com.example.maxspahn.studentbnb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    Toast toast;

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

        editName.setHint(user.getName());
        editSurname.setHint(user.getSurname());
        editUsername.setHint(user.getUsername());
        editPassword.setHint("*********");
        editTelephone.setHint(user.getTelephone());
        editEmail.setHint(user.getEmail());
        editRoom.setHint(user.getRoomNumber());

        buttonImage = (Button) findViewById(R.id.buttonImage);
        buttonImage.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toast = Toast.makeText(getApplicationContext(), "Information Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

}
