package com.example.mobidogi;

public class User {
  String name, username, password, dogname, rotu;
  int age, dogage;

  public User(String name, int age, String username, String password, String dogname, String rotu, int dogage) {
    this.name = name;
    this.age = age;
    this.username = username;
    this.password = password;
    this.dogname = dogname;
    this.rotu = rotu;
    this.dogage = dogage;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.age = -1;
    this.name = "";
    this.dogname = "";
    this.rotu = "";
    this.dogage = -1;
  }
}
