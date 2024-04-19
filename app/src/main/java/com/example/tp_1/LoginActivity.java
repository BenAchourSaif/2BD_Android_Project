package com.example.tp_1;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText login;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(EditText)findViewById(R.id.editTextTextEmailAddress3);
        pwd=(EditText)findViewById(R.id.editTextTextPwd);
    }


    public void login( View v){
    //a simple validation check
    if((login.getText().toString().length()<8)||(pwd.getText().toString().length()<4)){
        Toast.makeText(LoginActivity.this, "Vous devez saisir des données valide",Toast.LENGTH_SHORT);
    }else{
        SharedPreferences settings = getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("Login", login.getText().toString());
        editor.putString("Pwd", pwd.getText().toString());

        editor.apply();

        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent) ;
    }


    }

    public void inscri( View v){
        Intent intent = new Intent(LoginActivity.this,activity_Inscription.class);
        startActivity(intent) ;

    }

}