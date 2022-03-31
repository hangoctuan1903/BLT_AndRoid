package com.example.navigationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register extends AppCompatActivity {

    ImageView go_login;
    EditText editTextemail,editTextpassword,editTextconpassword;
    Button register_user;
    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDB = new MyDB(this);

        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        editTextconpassword = findViewById(R.id.password_con);
        register_user = findViewById(R.id.register);

        Register_user();

        go_login = findViewById(R.id.goto_login);
        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }

    private void Register_user() {
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextemail.getText().toString();
                String password = editTextpassword.getText().toString();
                String con_password = editTextconpassword.getText().toString();

                if (!password.equals(con_password)){
                    Toast.makeText(Register.this, "The password you insert not the same", Toast.LENGTH_SHORT).show();
                }
                else if (!isEmailValid(email)){
                    Toast.makeText(Register.this, "Is not Valid Email try Again", Toast.LENGTH_SHORT).show();
                }
                else if (!isPasswordValid(password)){
                    Toast.makeText(Register.this, "Too small password length!!!", Toast.LENGTH_SHORT).show();
                }
                else if (email.isEmpty()){
                    Toast.makeText(Register.this, "Mail Field Required!", Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(Register.this, "Password Field Required!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuser = myDB.checkuser(email);
                    if (checkuser == false) {
                        Boolean insert = myDB.insertData(email,password);
                        if (insert == true){
                            boolean isInserted = myDB.insertData(email,password);
                            new SweetAlertDialog(Register.this,SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Massage")
                                    .setContentText("You are Registered")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Intent i = new Intent(Register.this,Login.class);
                                            startActivity(i);
                                        }
                                    }).show();
                        }
                    }else {
                        boolean isInserted = myDB.insertData(email,password);
                        new SweetAlertDialog(Register.this,SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Massage")
                                .setContentText("User already exists!Please sign in")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent i = new Intent(Register.this,Login.class);
                                        startActivity(i);
                                    }
                                }).show();
                    }
                }
            }
        });
    }
    private boolean isEmailValid(String email){return email.contains("@");}
    private boolean isPasswordValid(String password){return password.length()>8;}
}