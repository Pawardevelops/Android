package com.example.test123.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.test123.models.Contacts;
import com.example.test123.params.Params;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler( Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.KEY_NAME + " TEXT,"
                + Params.KEY_PHONE + " TEXT" + ")";
            Log.d("sachinDB","query being run : "+CREATE_CONTACTS_TABLE);
            sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createContact(Contacts contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneNumber());

        sqLiteDatabase.insert(Params.TABLE_NAME,null,values);
        Log.d("sachinDB","value inserted ");
        sqLiteDatabase.close();
    }

    public ArrayList<Contacts> getAllContacts(){
        ArrayList<Contacts> contacts = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String select = "select * from "+Params.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                Contacts contact = new Contacts();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contacts.add(contact);
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return contacts;
    }
    public ArrayList<String> getAllNames(){
        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String select = "select Name from "+Params.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {

                names.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return names;
    }

    public int updateContact(Contacts contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_ID,contact.getId());
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneNumber());
        return sqLiteDatabase.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContactById(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public int getCount(){
        String select = "SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        int count =cursor.getCount();
        sqLiteDatabase.close();
        return count;
    }
}
