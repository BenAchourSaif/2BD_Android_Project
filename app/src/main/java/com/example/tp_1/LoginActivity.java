package com.example.tp_1;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
        Log.e("TAG", "Vous devez saisir des données valide");
        Toast.makeText(LoginActivity.this, "Vous devez saisir des données valide",Toast.LENGTH_SHORT);
    }else{


        sqliteDataBase dbHelper = new sqliteDataBase(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        if(utilisateurExiste(db,login.getText().toString(),pwd.getText().toString() )){
            Log.e("TAG", "******** Utilisateur Existe dans la base SQLITE ********");

            db.close();
            SharedPreferences settings = getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString("Login", login.getText().toString());
            editor.putString("Pwd", pwd.getText().toString());

            editor.apply();

            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent) ;


        }else {

            Toast.makeText(LoginActivity.this, "Login ou mot de passe incorrecte",Toast.LENGTH_SHORT);
        }
    }


    }
    public boolean utilisateurExiste(SQLiteDatabase db, String login, String motDePasse) {
        String query = "SELECT COUNT(*) FROM users WHERE Login = ? AND Pwd = ?";
        Cursor cursor = db.rawQuery(query, new String[]{login, motDePasse});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void inscri( View v){
        Intent intent = new Intent(LoginActivity.this,activity_Inscription.class);
        startActivity(intent) ;

    }

}