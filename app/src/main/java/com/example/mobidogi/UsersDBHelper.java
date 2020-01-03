package com.example.mobidogi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class UsersDBHelper extends SQLiteOpenHelper {
  //information of database
  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "users.db";
  public static final String TABLE_NAME = "Kayttajat";
  public static final String COLUMN_ID = "KayttajaID";
  public static final String COLUMN_NAME = "Nimi";
  public static final String COLUMN_AGE = "Ika";
  public static final String COLUMN_EMAIL = "Sahkoposti";
  public static final String COLUMN_PASSWORD = "Salasana";
  public static final String COLUMN_DOGNAME = "KoiranNimi";
  public static final String COLUMN_ROTU = "Rotu";

  //initialize the database
  public UsersDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_USERS_TABLE = "CREATE TABLE " +
      TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD
      + "TEXT, " + COLUMN_DOGNAME + " TEXT, " + COLUMN_ROTU + "TEXT " + ")";
    db.execSQL(CREATE_USERS_TABLE);
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
    values.put(COLUMN_ID, event.getDate());
    values.put(COLUMN_NAME, event.getEventDescription());
    values.put(COLUMN_AGE, event.getEventDescription());
    values.put(COLUMN_EMAIL, event.getEventDescription());
    values.put(COLUMN_PASSWORD, event.getEventDescription());
    values.put(COLUMN_AGE, event.getEventDescription());
    values.put(COLUMN_DOGNAME, event.getEventDescription());
    values.put(COLUMN_ROTU, event.getEventDescription());
    SQLiteDatabase db = this.getWritableDatabase();
    db.insert(TABLE_NAME, null, values);
    db.close();
  }
}

