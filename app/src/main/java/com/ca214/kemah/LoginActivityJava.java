package com.ca214.kemah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ca214.kemah.models.LoginModels;
import com.ca214.kemah.models.UserModels;
import com.google.gson.Gson;

public class LoginActivityJava extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private UserModels userModels;
    private Gson gson;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.fragment_login);
    }

    public void login(View view){
        username = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        sharedPreferences = getSharedPreferences("myAppData", MODE_PRIVATE);
        gson = new Gson();
        String jsonRegis = sharedPreferences.getString("regisData", "");
        if(jsonRegis.equals("")){
            Toast.makeText(getApplicationContext(), "Belum ada akun", Toast.LENGTH_SHORT).show();
        }else{
            userModels = gson.fromJson(jsonRegis, UserModels.class);
            if(!username.getText().toString().equals(userModels.getEmail()) && !password.getText().toString().equals(userModels.getPassword())){
                Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
            }else {
                LoginModels loginModels = new LoginModels();
                loginModels.setLogin(true);
                loginModels.setUsername(username.getText().toString());
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(loginModels);
                editor.putString("loginData", json);
                editor.apply();
                startActivity(new Intent(LoginActivityJava.this, MainActivityJava.class));
            }
        }
    }

    public void goRegister(View view){
        startActivity(new Intent(LoginActivityJava.this, RegisterActivityJava.class));
    }
}
