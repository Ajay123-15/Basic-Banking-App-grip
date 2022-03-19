package com.example.banking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseUserNames extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseUserNames(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1523455555,'Roshan',4888.00,'Roshan@gmail.com','XXXXXXXXXXXX1234','ABC012367')");
        db.execSQL("insert into user_table values(8484654654,'Sunil',4582.00,'Sunil@gmail.com','XXXXXXXXXXXX4567','ABC012567')");
        db.execSQL("insert into user_table values(4851245555,'Suraj',3452.05,'Suraj@gmail.com','XXXXXXXXXXXX7845','ABC012347')");
        db.execSQL("insert into user_table values(8456456945,'Ankit',7426.09,'Ankit@gmail.com','XXXXXXXXXXXX7454','ABC012345')");
        db.execSQL("insert into user_table values(1654845656,'Rahul',23650.02,'Rahul@gmail.com','XXXXXXXXXXXX4512','ABC012347')");
        db.execSQL("insert into user_table values(8554445654,'Ashish',12364.15,'Ashish@gmail.com','XXXXXXXXXXXX555','ABC012347')");
        db.execSQL("insert into user_table values(8741852655,'Amit',5746.00,'Amit@gmail.com','XXXXXXXXXXXX1848','ABC012347')");
        db.execSQL("insert into user_table values(8451411411,'Sanjeet',6325.22,'Sanjeet@gmail.com','XXXXXXXXXXXX4875','ABC012567')");
        db.execSQL("insert into user_table values(7451256344,'Siddhart',4398.46,'Sidd@gmail.com','XXXXXXXXXXXX7845','ABC012347')");
        db.execSQL("insert into user_table values(7845125884,'Mahesh',8273.90,'Mahesh@gmail.com','XXXXXXXXXXXX7956','ABC0167')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date, String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}