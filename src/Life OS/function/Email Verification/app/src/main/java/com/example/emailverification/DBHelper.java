package com.example.emailverification;


public class DBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "LifeOs.db"; 
    public static final String TABLE_1 = "USER";
    
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrage(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertData(String tableName, ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName, null, values);
    }

    public void updateData(String tableName, ContentValues newValues, String selection, String[] selectionArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.update(tableName, newValues, selection, selectionArgs);
    }


}