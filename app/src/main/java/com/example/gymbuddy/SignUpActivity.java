package com.example.gymbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText txtEmail,txtNickname,txtPassword;
    Button btnSignUp;
    TextView haveAnAccount;
    FirebaseAuth firebaseAuth;
    ProgressDialog pd ;

    //Init for store data
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Action Bar and it's title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init views
        txtEmail = findViewById(R.id.editTextEmailSignUp);
        txtNickname = findViewById(R.id.editTextNicknameSignUp);
        txtPassword = findViewById(R.id.editTextPasswordSignUp);
        btnSignUp = findViewById(R.id.signUpButtonSignUp);
        haveAnAccount = findViewById(R.id.haveAnAccount);

        //FireBase init
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Init progress dialog
        pd = new ProgressDialog(this);
        pd.setMessage("Creating Profile.....");


        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ŞİFRENİN 8 KARAKTER OLMASINI İSTEMEMİZ GEREK.
                //Kayıt Olma İşlemleri.....
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                final String nickname = txtNickname.getText().toString();
                pd.show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(SignUpActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pd.dismiss();
                        Toast.makeText(SignUpActivity.this,"User created.",Toast.LENGTH_LONG).show();

                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        //Get user uuid and email
                        String uuid = user.getUid();
                        String email = user.getEmail();

                        //user email, nickname ve createdDate tarihini tutacağız.
                        HashMap<String,Object> userCreatedInfos = new HashMap<>();
                        userCreatedInfos.put("useremail",email);
                        userCreatedInfos.put("nickname",nickname);
                        userCreatedInfos.put("name","");    //Field'll edit in profile
                        userCreatedInfos.put("uuid",uuid);  //Field'll edit in profile
                        userCreatedInfos.put("imageLink","");   //Field'll edit in profile
                        userCreatedInfos.put("bio","");   //Field'll edit in profile

                        //Firebasedatabase instance
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        //path to 'userinfos'
                        DatabaseReference reference = database.getReference("UserInfos");
                        //put data within hashmap
                        reference.child(uuid).setValue(userCreatedInfos);



                        Intent intent = new Intent(SignUpActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //Go previous activity.
        return super.onSupportNavigateUp();
    }
    }
