package com.example.tp_1;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        layout=(ConstraintLayout)findViewById(R.id.layout);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String sexe = extras.getString("sexe");
            //Toast.makeText(HomeActivity.this, "Vous êtes un(e) "+sexe,Toast.LENGTH_SHORT);
            if(sexe=="homme")
                layout.setBackgroundColor(0xFF3F51B5);
            else
                layout.setBackgroundColor(0xFFF436A8);
        }
    }

    public void logout( View v){

            SharedPreferences settings = getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();

            editor.remove("Login");
            editor.remove("Pwd");

            editor.apply();

            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent) ;

    }
}