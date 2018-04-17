package com.example.quocq.dawd_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocq on 04/17/2018.
 */

public class BookHelper extends SQLiteOpenHelper {
    public BookHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BookDB.db";

    private static final String TABLE_NAME = "Book";

    private static final String COLUMN_CODE= "code";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_PRICE= "price";
    private static final String COLUMN_AUTHOR= "author";
    String create_table;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        create_table = "create table " + TABLE_NAME +
                "(" + COLUMN_CODE + " int primary key, " +
                COLUMN_TITLE + " text," +
                COLUMN_PRICE + " int," +
                COLUMN_AUTHOR + " text)";

        sqLiteDatabase.execSQL(create_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addBook(Book b) {
        SQLiteDatabase db = getWritableDatabase();
//        Log.i("book data: ", b.toString());
//        Log.i("query: ", create_table);
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE, b.getCode());
        values.put(COLUMN_AUTHOR, b.getAuthor());
        values.put(COLUMN_PRICE, b.getPrice());
        values.put(COLUMN_TITLE, b.getTitle());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {
                Book b = new Book();
                b.setCode(cursor.getInt(0));
                b.setTitle(cursor.getString(1));
                b.setPrice(cursor.getInt(2));
                b.setAuthor(cursor.getString(3));
                list.add(b);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

//        list.add(new Book(123, "asd",3, "aa"));
        return list;
    }



    public boolean deleteBook(int code)
    {
        boolean result = false;

//        String query = "Select * from " + TABLE_NAME + " where " + COLUMN_CODE + " = \"" + code + "\"";
        String query = "Select * from " + TABLE_NAME + " where " + COLUMN_CODE + " = " + code ;

        Log.i("query delete: ", query);
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Book e = new Book();

        if(cursor.moveToFirst())
        {
            e.setCode(cursor.getInt(0));
            db.delete(TABLE_NAME, COLUMN_CODE+ " = ?",
                    new String[] { e.getCode() +""});

            cursor.close();

            result = true;
        }

        db.close();

        return result;
    }
}
