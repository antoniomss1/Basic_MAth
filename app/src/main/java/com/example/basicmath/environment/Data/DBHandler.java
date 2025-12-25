package com.example.basicmath.environment.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.basicmath.models.GameData;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DBContract.TABLE_NAME;
    private static final int DATABASE_VERSION = 2;

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
//                        DBContract.GAME_FIELD_TIME_SPENT + " INTEGER NOT NULL, " +
                        DBContract.GAME_FIELD_WRONG_PROBLEMS + " INTEGER NOT NULL, " +
                        DBContract.GAME_FIELD_BIGGEST_SEQ + " INTEGER NOT NULL" +
                        ");";

        db.execSQL(createTablePastGame);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(this.DATABASE_VERSION < 2){
            db.execSQL("DROP TABLE IF EXISTS "+DBContract.TABLE_NAME);
            onCreate(db);
        }
    }

    public long addPastGame(SQLiteDatabase db, GameData game){
        ContentValues values = new ContentValues();
        System.out.println("adding past game: "+game.toString());
        values.put(DBContract.GAME_FIELD_DATE, game.getDateTime());
        values.put(DBContract.GAME_FIELD_NUMBER_OF_PROBLEMS, game.getNumberOfProblems());
        values.put(DBContract.GAME_FIELD_BIGGEST_SEQ, game.getBiggestSequence());
        values.put(DBContract.GAME_FIELD_AVERAGE, game.getAverageTime());
        values.put(DBContract.GAME_FIELD_WRONG_PROBLEMS, game.getWrongProblems());

        long id = db.insert(DBContract.TABLE_NAME, null, values);
        System.out.println("ID: "+id);
        db.close();

        return id;
    }

    public Cursor getAllGamesInDb(SQLiteDatabase db){
        return db.rawQuery(
                "SELECT * FROM " + DBContract.TABLE_NAME + " ORDER BY id DESC",
                null
        );
    }

    public void deleteTask(int id, SQLiteDatabase db){
        db.delete(DBContract.TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }


}
