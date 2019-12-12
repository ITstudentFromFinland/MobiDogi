package com.example.mobidogi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBHelper extends SQLiteOpenHelper {
  //information of database
  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "treenipaivakirja.db";
  public static final String TABLE_NAME = "Treenit";
  public static final String COLUMN_DATE = "TreeniPaivays";
  public static final String COLUMN_DESCRIPTION = "TreeniKuvaus";

  //initialize the database
  public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
      String CREATE_TRAINING_TABLE = "CREATE TABLE " +

        TABLE_NAME + "(" + COLUMN_DATE + " INTEGER PRIMARY KEY," + COLUMN_DESCRIPTION + " TEXT " + ")";
        db.execSQL(CREATE_TRAINING_TABLE);
  }
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }
  public String loadHandler() {
    String result = "";
    String query = "Select*FROM " + TABLE_NAME;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(query, null);
    while (cursor.moveToNext()) {
      int result_0 = cursor.getInt(0);
      String result_1 = cursor.getString(1);
      result += String.valueOf(result_0) + " " + result_1 + System.getProperty("line.separator");
    }
    cursor.close();
    db.close();
    return result;
  }
  public void addHandler(DisplayEvent event) {
    ContentValues values = new ContentValues();
    values.put(COLUMN_DATE, event.getDate());
    values.put(COLUMN_DESCRIPTION, event.getEventDescription());
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(TABLE_NAME, null, values);
    db.close();
    }
  }

