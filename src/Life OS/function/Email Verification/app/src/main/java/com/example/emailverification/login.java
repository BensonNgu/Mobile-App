package com.example.emailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        EditText etPassword = findViewById(R.id.tvPasswordLogin);
        String password = etPassword.getText().toString();
        if(!(password.isEmpty()) && password.equalsIgnoreCase("123456")){
            finish();
            Toast.makeText(this, "You have login successfully", Toast.LENGTH_SHORT);
        }else if(password.isEmpty()){
            Toast.makeText(this, "Password cannot left blank", Toast.LENGTH_SHORT);
        }
    }
}