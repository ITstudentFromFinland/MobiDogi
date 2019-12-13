package com.example.mobidogi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Treenipaivakirja extends AppCompatActivity {

  TextView lst;
  EditText eventdate;
  EditText eventdescription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.treenipaivakirja_activity);

    lst = (TextView) findViewById(R.id.list);
    lst.setMovementMethod(new ScrollingMovementMethod());
    eventdate = (EditText) findViewById(R.id.eventDate);
    eventdescription = (EditText) findViewById(R.id.eventDescription);
  }

  public void loadEvents(View view) {
    DBHelper dbHandler = new DBHelper(this, null, null, 1);
    lst.setText(dbHandler.loadHandler());
    eventdate.setText("");
    eventdescription.setText("");
  }

  public void addEvent(View view) {
    DBHelper dbHandler = new DBHelper(this, null, null, 1);
    int date = Integer.parseInt(eventdate.getText().toString());
    String description = eventdescription.getText().toString();
    DisplayEvent event = new DisplayEvent(date, description);

    dbHandler.addHandler(event);
    eventdate.setText("");
    eventdescription.setText("");
  }
}

