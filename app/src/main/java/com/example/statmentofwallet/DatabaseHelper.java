package com.example.statmentofwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "databas_name";
    private static final String TABLE_NAME = "table_name";
    public static final String AMOUNT = "AMOUNT";
    public static final String REASON = "REASON";
    public static final String DAY = "DAY";
    public static final String HOUR = "HOUR";
    public static final String ID = "id";

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AMOUNT + " TEXT, " + REASON + " TEXT, " + DAY + " TEXT, " + HOUR + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(Data data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AMOUNT,data.getMoney());
        contentValues.put(REASON,data.getReason());
        contentValues.put(DAY,data.getDay());
        contentValues.put(HOUR,data.getTime());

        long insert = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (insert == -1){
            return false;
        }else {
            return true;
        }
    }
    public List<Data> getAllData(){
        List<Data> returnlist = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            //returnlist.add(cursor.getString(cursor.getColumnIndex("txt")));
            returnlist.add(new Data(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnlist;
    }

    public boolean deliteOne(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + data.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
}
