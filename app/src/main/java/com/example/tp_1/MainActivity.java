package com.example.tp_1;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginCheck( View v){

        SharedPreferences preferences  =  getDefaultSharedPreferences(this);
        String login = preferences.getString("Login","isNotConnected");

        if ( login == "isNotConnected"){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent) ;
        }else{
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent) ;
        }
    }
}