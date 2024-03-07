package com.example.emailverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "You have enter Main Activity", Toast.LENGTH_SHORT);
    }

    public void signUp(View view){
        Intent intent = new Intent(this, sign_up.class);
        startActivity(intent);
    }

    public void userLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}