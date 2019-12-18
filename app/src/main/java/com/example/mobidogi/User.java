package com.example.mobidogi;

public class User {
  String name, username, password, dogname, rotu;
  int age;

  public User(String name, String username, String password, int age, String dogname, String rotu) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.age = age;
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
