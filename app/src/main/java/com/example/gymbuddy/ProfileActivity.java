package com.example.gymbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView profileTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Action Bar and it's title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");

        //inits
        firebaseAuth = FirebaseAuth.getInstance();
        profileTextView = findViewById(R.id.profileTv);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //For now just take the email, then we will update it as username.
        profileTextView.setText(user.getEmail());
    }
    /*private void checkUserStatus(){
        //Current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            //Stay logged in.
        }else{
            //User is not sign in we should go to signin activity
            Intent intent = new Intent(ProfileActivity.this,SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }*/

    /*protected void onStart() {
        checkUserStatus();
        super.onStart();
    }*/

    //Inflate options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //When menu item clicks.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout){
            firebaseAuth.signOut();
            startActivity(new Intent(ProfileActivity.this,SignInActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}