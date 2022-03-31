package com.example.navigationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    ImageView go_reg;
    EditText editTextEmail,editTextPassword;
    Button button_login;
    MyDB myDBpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDBpassword = new MyDB(this);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        button_login = findViewById(R.id.login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempt_login();
            }
        });

        go_reg = findViewById(R.id.goto_reg);

        go_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    private void attempt_login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!isEmailValid(email)){
            Toast.makeText(this, "Email Valid", Toast.LENGTH_SHORT).show();
        }
        if (!isPasswordValid(password)){
            Toast.makeText(this, "Password Valid", Toast.LENGTH_SHORT).show();
        }
        Cursor res  = myDBpassword.login_user(email,password);
        Boolean checkuserpass = myDBpassword.checkuserpassword(email,password);
        if (checkuserpass == true){
            final Intent i = new Intent(Login.this,Home.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show();
        }
//        if (res.getCount()==1){
//
//        }
//        else {
//            Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show();
//        }
    }

    private boolean isEmailValid(String email){return email.contains("@");}
    private boolean isPasswordValid(String password){return password.length()>8;}
}