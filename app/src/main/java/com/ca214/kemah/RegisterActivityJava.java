package com.ca214.kemah;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ca214.kemah.models.UserModels;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Objects;

public class RegisterActivityJava extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Button registerButton;
    private EditText firstName, lastName, email, phone, password, repassword;
    private View registerForm;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.fragment_register);
        registerForm = findViewById(R.id.includeRegisterForm);
        registerButton = registerForm.findViewById(R.id.btnRegistrasi);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextPhoneNumber);
        password = findViewById(R.id.editTextPassword);
        repassword = findViewById(R.id.editTextRepeatPassword);
        sharedPreferences = getSharedPreferences("myAppData", MODE_PRIVATE);

        registerButton.setOnClickListener(view -> {
            UserModels userRegis = new UserModels();
            userRegis.setFirstName(firstName.getText().toString());
            userRegis.setLastName(lastName.getText().toString());
            userRegis.setEmail(email.getText().toString());
            userRegis.setPhone(phone.getText().toString());
            userRegis.setPassword(password.getText().toString());
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor =sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(userRegis);
            editor.putString("regisData", json);
            editor.apply();
            startActivity(new Intent(RegisterActivityJava.this, LoginActivityJava.class));
        });
    }
}
