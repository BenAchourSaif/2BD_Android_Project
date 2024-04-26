package com.example.tp_1;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_Inscription extends AppCompatActivity {

    private EditText nom;
    private EditText email;
    private EditText pwd;
    private EditText tel;
    private RadioButton homme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        nom=(EditText)findViewById(R.id.editTextNom);
        email=(EditText)findViewById(R.id.editTextTextEmailAddress3);
        pwd=(EditText)findViewById(R.id.editTextTextPwd);
        tel=(EditText)findViewById(R.id.editTextTel);
        homme=(RadioButton)findViewById(R.id.radiohomme);
    }

    public void inscription( View v){
        //a simple validation check
        if((nom.getText().toString().length()<8)||(email.getText().toString().length()<15)
                ||(pwd.getText().toString().length()<4)||(tel.getText().toString().length()!=8)
        ){
            Log.e("TAG", "Vous devez saisir des données valide");
             Toast.makeText( activity_Inscription.this, "Vous devez saisir des données valide",Toast.LENGTH_SHORT);
        }else{
            SharedPreferences settings = getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString("Login", email.getText().toString());
            editor.putString("Pwd", pwd.getText().toString());
            editor.putString("nom", nom.getText().toString());
            editor.putString("tel", tel.getText().toString());
            editor.putBoolean("homme", homme.isChecked());

            editor.apply();


            String sexe;
                if(homme.isChecked())
                {
                    sexe="homme";
                }
                else
                {
                        sexe="femme";
                }

            sqliteDataBase dbHelper = new sqliteDataBase(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            /*
            ContentValues values = new ContentValues();
            values.put("Login", email.getText().toString());
            values.put("Pwd", pwd.getText().toString());
            values.put("nom", nom.getText().toString());
            values.put("tel", tel.getText().toString());
            values.put("homme", sexe);

            db.insert("users", null, values);
*/

            String sql = "INSERT INTO users (Login, Pwd,nom,tel, homme) VALUES ('" + email.getText().toString() + "', '"+pwd.getText().toString()
                    + "', '"+nom.getText().toString()+ "', '"+tel.getText().toString()+ "', '"+sexe+"')";


            db.execSQL(sql);
            db.close();
            Log.e("TAG", "après inscription");



            Intent intent = new Intent(this,HomeActivity.class);

          intent.putExtra("sexe", sexe);

            startActivity(intent) ;
        }
    }

}