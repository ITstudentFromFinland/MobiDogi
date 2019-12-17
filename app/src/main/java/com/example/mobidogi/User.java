package com.example.mobidogi;

public class User {
  String name, username, password, dogname, rotu;
  int age;

  public User(String name, int age, String username, String password, String dogname, String rotu) {
    this.name = name;
    this.age = age;
    this.username = username;
    this.password = password;
    this.dogname = dogname;
    this.rotu = rotu;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.age = -1;
    this.name = "";
    this.dogname = "";
    this.rotu = "";
  }
}
