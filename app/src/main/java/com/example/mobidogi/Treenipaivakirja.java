package com.example.mobidogi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.mobidogi.DBHelper.COLUMN_DATE;
import static com.example.mobidogi.DBHelper.COLUMN_DESCRIPTION;


public class Treenipaivakirja extends AppCompatActivity {

  TextView lst;
  EditText eventdate;
  EditText eventdescription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.treenipaivakirja_activity);

    lst = (TextView)findViewById(R.id.list);
    eventdate = (EditText)findViewById(R.id.eventDate);
    eventdescription = (EditText)findViewById(R.id.eventDescription);
  }

  public void loadEvents(View view) {
    DBHelper dbHandler = new DBHelper(this, COLUMN_DATE, null, 1);
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

  public void findEvents(View view) {
    DBHelper dbHandler = new DBHelper(this, null, null, 1);
    DisplayEvent event = dbHandler.findHandler(eventdescription.getText().toString());
    if (event != null) {
      lst.setText(String.valueOf(event.getDate()) + " " + event.getEventDescription());

      } else {
      lst.setText("Ei löydy");
      eventdate.setText("");
      eventdescription.setText("");
    }
  }

  public void updateEvent(View view) {
    DBHelper dbHandler = new DBHelper(this, null, null, 1);
    boolean result = dbHandler.updateHandler(Integer.parseInt(
      eventdate.getText().toString()), eventdescription.getText().toString());
    if (result) {
      eventdate.setText("");
      eventdescription.setText("");
      lst.setText("Merkintä päivitetty");
    } else
      eventdate.setText("Ei löydy");
  }
}


