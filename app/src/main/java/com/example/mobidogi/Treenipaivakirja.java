package com.example.mobidogi;


import java.util.Calendar;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Treenipaivakirja extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.treenipaivakirja_activity);

    Calendar.getInstance();

    Button btn_add_event = findViewById(R.id.btn_add_event);
    btn_add_event.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Calendar beginTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_INSERT, Uri.parse("content://events"));
        intent.setData(Events.CONTENT_URI);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        intent.putExtra(Events.TITLE, "Treenin nimi");
        intent.putExtra(Events.DESCRIPTION, "Treenin kuvaus");
        intent.putExtra(Events.EVENT_LOCATION, "Treenipaikka");
        intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
        intent.putExtra(Intent.EXTRA_EMAIL, "mobidogisovellus@gmail.com");
        startActivity(intent);
      }
    });

    Button btn_show_event = findViewById(R.id.btn_show_event);
    btn_show_event.setOnClickListener(new View.OnClickListener() {


      @Override
      public void onClick(View v) {

        long eventID = 0;

        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, eventID);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(builder.build());
        startActivity(intent);
      }
    });
  }
}
