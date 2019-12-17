package com.example.mobidogi;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {
  public static final String SP_NAME = "userDetails";
  SharedPreferences userLocalDatabase;

  public UserLocalStore(Context context) {
    userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
  }

  public void storeUserData(User user) {
    SharedPreferences.Editor spEditor = userLocalDatabase.edit();
    spEditor.putString("name", user.name);
    spEditor.putInt("age", user.age);
    spEditor.putString("username", user.username);
    spEditor.putString("password", user.password);
    spEditor.putString("dogname", user.dogname);
    spEditor.putString("rotu", user.rotu);
    spEditor.commit();
  }

  public User getLoggedInUser() {
    String name = userLocalDatabase.getString("name", "");
    int age = userLocalDatabase.getInt("age", -1);
    String username = userLocalDatabase.getString("username", "");
    String password = userLocalDatabase.getString("password", "");
    String dogname = userLocalDatabase.getString("dogname", "");
    String rotu = userLocalDatabase.getString("rotu", "");

    User storedUser = new User(name, age, username, password, dogname, rotu);

    return storedUser;
  }

  public void setUserLoggedIn(boolean loggedIn) {
    SharedPreferences.Editor spEditor = userLocalDatabase.edit();
    spEditor.putBoolean("loggedIn", loggedIn);
    spEditor.commit();
  }
}
