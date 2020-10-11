package com.sejjoh.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    MaterialButton mRegister;
    TextInputEditText mEmail;
    TextInputEditText mName;
    TextInputEditText mPassword;
    TextInputEditText mConfirm;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegister = findViewById(R.id.btnRegister);
        mEmail = findViewById(R.id.txtEmail);
        mName = findViewById(R.id.txtName);
        mPassword = findViewById(R.id.txtPassword);
        mConfirm = findViewById(R.id.txtConfirm);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}