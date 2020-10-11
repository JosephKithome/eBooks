 package com.sejjoh.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

 public class LoginActivity extends AppCompatActivity {
    MaterialButton mLogin;
    MaterialButton mGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = findViewById(R.id.btnLogin);
        mGoRegister =findViewById(R.id.materialButton3);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainIntent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });
        mGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(MainIntent);

            }
        });
    }
}