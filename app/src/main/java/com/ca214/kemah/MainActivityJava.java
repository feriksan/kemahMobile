package com.ca214.kemah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ca214.kemah.models.LoginModels;
import com.ca214.kemah.models.UserModels;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class MainActivityJava extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private LoginModels loginModels;
    private Gson gson;
    private View mainContent;
    private TextView welcomeMsg;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("myAppData", MODE_PRIVATE);
        gson = new Gson();
        String json = sharedPreferences.getString("loginData", "");
        if(json.equals("")){
            Toast.makeText(getApplicationContext(), "Anda Belum Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivityJava.this, LoginActivityJava.class));
        }else{
            loginModels = gson.fromJson(json, LoginModels.class);
            welcome();
        }
    }

    public void welcome(){
        mainContent = findViewById(R.id.layoutContentMain);
        welcomeMsg = mainContent.findViewById(R.id.textViewWelcome);
        welcomeMsg.setText(loginModels.getUsername());
        logoutButton = mainContent.findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(view -> {
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("loginData").apply();
            startActivity(new Intent(MainActivityJava.this, LoginActivityJava.class));
        });

    }
}
