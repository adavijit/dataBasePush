package com.example.avijit.vt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        ((TextView)findViewById(R.id.email)).setText(email);
    }

    public void Logout(View view) {
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
