package com.example.basicmath.environment.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DBContract.TABLE_NAME;
    private static final int DATABASE_VERSION = 1;

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePastGame =
                "CREATE TABLE " + DBContract.TABLE_NAME + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DBContract.GAME_FIELD_DATE + " TEXT NOT NULL, " +
                        DBContract.GAME_FIELD_NUMBER_OF_PROBLEMS + " INTEGER NOT NULL, " +
                        DBContract.GAME_FIELD_AVERAGE + " REAL NOT NULL, " +
                        DBContract.GAME_FIELD_TIME_SPENT + " INTEGER NOT NULL, " +
                        DBContract.GAME_FIELD_ACCURACY + " REAL NOT NULL, " +
                        DBContract.GAME_FIELD_BIGGEST_SEQ + " INTEGER NOT NULL" +
                        ");";

        db.execSQL(createTablePastGame);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
