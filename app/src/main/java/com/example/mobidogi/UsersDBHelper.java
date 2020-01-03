package com.example.mobidogi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import com.example.mobidogi.UsersDBHelper;

public class UsersDBHelper extends SQLiteOpenHelper {
  //information of database
  private static final String DATABASE_NAME = "users.db";
  private static final int DATABASE_VERSION = 1;
  public static final String TABLE_NAME = "Kayttajat";
  public static final String COLUMN_ID = "KayttajaID";
  public static final String COLUMN_NAME = "Nimi";
  public static final String COLUMN_AGE = "Ika";
  public static final String COLUMN_EMAIL = "Sahkoposti";
  public static final String COLUMN_PASSWORD = "Salasana";
  public static final String COLUMN_DOGNAME = "KoiranNimi";
  public static final String COLUMN_ROTU = "Rotu";

  public static final String CREATE_USERS_TABLE = "CREATE TABLE " +
    TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " INTEGER, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD
    + "TEXT, " + COLUMN_DOGNAME + " TEXT, " + COLUMN_ROTU + "TEXT " + ")";

  //initialize the database
  public UsersDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_USERS_TABLE);
  }
  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
  }

    public void addUser(User user) {

      UsersDBHelper datab = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_NAME, user.name);
      values.put(COLUMN_AGE, user.age);
      values.put(COLUMN_EMAIL, user.email);
      values.put(COLUMN_PASSWORD, user.password);
      values.put(COLUMN_DOGNAME, user.dogName);
      values.put(COLUMN_ROTU, user.rotu);

      long todo_id = datab.insert(TABLE_NAME, null, values);
    }

    public User Authenticate(User user) {
      UsersDBHelper db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_NAME,
        new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_DOGNAME, COLUMN_ROTU},
        COLUMN_EMAIL + "=?",
        new String[]{user.email},
        null, null, null);

      if(cursor != null && cursor.moveToFirst()&& cursor.getCount()0) {
        User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(3));

        if (user.password.equalsIgnoreCase(user1.password)) {
          return user1;
        }
      }

      return null;
    }

    public boolean isEmailExists(String Sahkoposti) {
      UsersDBHelper db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_NAME,
        new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_DOGNAME, COLUMN_ROTU},
        COLUMN_EMAIL + "=?",
        new String[]{Sahkoposti},
        null, null, null);

      if (cursor != null && cursor.moveToFirst() cursor.getCount()>0) {

        return true;
      }

      return false;
    }
  }
