package com.example.mobidogi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class UsersDBHelper extends SQLiteOpenHelper {
  //information of database
  private static final String DATABASE_NAME = "users.db";
  private static final int DATABASE_VERSION = 1;
  public static final String TABLE_USERS = "users";
  public static final String COLUMN_ID = "id";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_PASSWORD = "password";

  public static final String SQL_TABLE_USERS = " CREATE TABLE " +
    TABLE_USERS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT " + " ) ";

  //initialize the database
  public UsersDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(SQL_TABLE_USERS);
  }
  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
  }

    public void addUser(User user) {

      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_EMAIL, user.email);
      values.put(COLUMN_PASSWORD, user.password);

      long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_USERS,
        new String[]{COLUMN_ID,COLUMN_EMAIL, COLUMN_PASSWORD},
        COLUMN_EMAIL + "=?",
        new String[]{user.email},
        null, null, null);

      if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
        User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));

        if (user.password.equalsIgnoreCase(user1.password)) {
          return user1;
        }
      }

      return null;
    }

    public boolean isEmailExists(String email) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_USERS,
        new String[]{COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD},
        COLUMN_EMAIL + "=?",
        new String[]{email},
        null, null, null);

      if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

        return true;
      }

      return false;
    }
  }
