package com.test.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="Account.db";

    public DBHelper(Context context) {
        super(context,"Account.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT,name TEXT,phone TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String username,String password,String name,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("phone",phone);
        long result= db.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where username=?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
    public Boolean checkusernamepassword(String username,String pass) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where username = ? and password=?",new String[]{username,pass});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
}

