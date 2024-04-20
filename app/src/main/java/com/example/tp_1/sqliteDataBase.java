package com.example.tp_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqliteDataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MaBaseDeDonnees";

    public sqliteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer les tables de la base de données
        db.execSQL("CREATE TABLE MaTable (id INTEGER PRIMARY KEY, nom TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettre à jour la base de données en cas de changements de schéma
        db.execSQL("DROP TABLE IF EXISTS MaTable");
        onCreate(db);
    }
}