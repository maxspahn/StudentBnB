package com.example.maxspahn.studentbnb;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Arturo on 07/06/2017.
 */

public class UserThread extends Thread {

    User user;
    String username;

    public UserThread(String username) {
        this.username = username;
    }

    public void run() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Get the user.
        DatabaseReference ref  = database.getReference("users");

        DatabaseReference ref2 = ref.child(username);

        // Read from the database and check if userName fits to password.
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user = (User) dataSnapshot.getValue(User.class);
                System.out.println("Aaaaaaaaaaaaaaaaaaateeeeenciooooooooooon el thread is done ");
                System.out.println(user.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("CREATION", "Failed to read value.", error.toException());
            }
        });

        //System.out.println(user.getName());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
