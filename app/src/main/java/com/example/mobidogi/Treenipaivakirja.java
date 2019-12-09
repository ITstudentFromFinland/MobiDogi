package com.example.mobidogi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Treenipaivakirja extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.treenipaivakirja_activity);
    CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String selectedDate = sdf.format(new Date(calendar.getDate()));
    calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

      @Override
      public void onSelectedDayChange(CalendarView view, int year, int month,
                                      int day) {
        month++;
        String stringMonth;
        String stringDay;
        if(month<10){stringMonth ="0"+ String.valueOf(month);} else{stringMonth = String.valueOf(month);}
        if(day<10){stringDay ="0"+ String.valueOf(day);} else{stringDay = String.valueOf(day);}

        currentDate =stringDay+"/"+stringMonth+"/"+ String.valueOf(year);
        Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + day + "\n" + "Month = " + month + "\n" + "Year = "
          + year, Toast.LENGTH_LONG).show();
      }
    });
    if(currentDate ==null){
      currentDate =selectedDate;}
    dbEvent = new SqlBdHelper(this,null,null,1);
  }

  public void viewEdit(View v){
    Intent intent = new Intent(this, ViewEvents.class);
    intent.putExtra("date", currentDate);
    startActivity(intent);
  }

  public void create(View v){
    Intent intent = new Intent(this, CreateEvent.class);
    intent.putExtra("date", currentDate);
    startActivity(intent);

  }

  @Override
  public void onBackPressed() {
    finish();
  }
}
