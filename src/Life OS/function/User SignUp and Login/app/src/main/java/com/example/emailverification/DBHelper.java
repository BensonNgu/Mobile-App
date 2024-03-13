package com.example.emailverification;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lifeOS.db";

    // USER TABLE
    public static final String USER_TABLE = "USER";
    public static final String USER_COL1 = "Name";
    public static final String USER_COL2 = "Email";
    public static final String USER_COL3 = "Password";

    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE USER (" +
            "UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Username TEXT NOT NULL UNIQUE," +
            "Password TEXT NOT NULL UNIQUE," +
            "Email TEXT NOT NULL UNIQUE);";
    //

    public static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){

        }
    }

    public long insertData(ContentValues values, String TABLE_NAME){
        SQLiteDatabase db = getWritableDatabase();
        long rowsAffected = db.insert(TABLE_NAME, null, values);
        db.close();
        return rowsAffected;
    }

    public int update(ContentValues values, String tableName, String selection, String[] selectionArgs){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.update(tableName, values, selection, selectionArgs);
        db.close();
        return rowsAffected;
    }
}
