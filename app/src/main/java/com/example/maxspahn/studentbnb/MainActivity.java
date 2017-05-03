package com.example.maxspahn.studentbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("hallo Perdro");
        System.out.println("hallo Max");
        System.out.println("hallo Arturo :)");

        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        launchActivity();
                        break;

                }
            }
        });
    }

    private void launchActivity(){
        Intent intent = new Intent(this, SearchRoomActivity.class);
        startActivity(intent);
    }
}
