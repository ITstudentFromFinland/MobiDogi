package com.example.mobidogi;

public class DisplayEvent {
  private int eventDate;
  private String eventDescription;

  // constructors
  public DisplayEvent() {
  }

  public DisplayEvent(int date, String description) {
    this.eventDate = date;
    this.eventDescription = description;
  }

  // properties
  public void setDate(int date) {
    this.eventDate = date;
  }

  public int getDate() {
    return this.eventDate;
  }

  public void setEventDescription(String description) {
    this.eventDescription = description;
  }

  public String getEventDescription() {
    return this.eventDescription;
  }
}
