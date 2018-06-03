package com.example.avijit.vt;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth=FirebaseAuth.getInstance();
        TextView b1= (TextView) findViewById(R.id.goback);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(signup.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void RegisterUser(View view) {
        final String name=((EditText)findViewById(R.id.reg_name)).getText().toString();
        String email=((EditText)findViewById(R.id.reg_email)).getText().toString();
        String pass=((EditText)findViewById(R.id.reg_pass)).getText().toString();
        if (email.equals("") || pass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"fill-up correctly",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (!email.matches(emailPattern) || pass.length() < 6)
            {
                Toast.makeText(getApplicationContext(),"Password must be at least 6 characters ",Toast.LENGTH_SHORT).show();
            }
            else
            {
                final AuthResult[] result = new AuthResult[1];
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                        else {
                            Log.i("Test","Unsuccessfull");
                        }
                    }
                });
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
