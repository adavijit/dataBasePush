package com.example.avijit.vt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.avijit.vt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth=FirebaseAuth.getInstance();
        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(getApplicationContext(),"Welcome "+user.getEmail(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),UserDetails.class);
                    intent.putExtra("email",user.getEmail());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Login!!!",Toast.LENGTH_SHORT).show();
                }
            }
        };


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListner);
    }


    public void SignIn(View view) {




        String email=((EditText)findViewById(R.id.email)).getText().toString();
        String pass=((EditText)findViewById(R.id.pass)).getText().toString();
        if (email.matches("")|| pass.matches(""))
        {
            Toast.makeText(getApplicationContext(),"enter correctly",Toast.LENGTH_SHORT).show();
        }
        else {


            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    public void Register(View view) {
        startActivity(new Intent(this,signup.class));
        finish();
    }
}