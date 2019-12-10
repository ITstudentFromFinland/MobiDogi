package com.example.mobidogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Treenipaivakirja extends AppCompatActivity {
  TextView textViewTrainingEvents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.treenipaivakirja_activity);

    textViewTrainingEvents = (TextView) findViewById(R.id.textView);

  }

  public void addEvent(View view) {
    Intent intAddEvent = new Intent(this, AddEventActivity.class);
    startActivityForResult(intAddEvent, 2);

  }

  String[] previousValue = new String[10000];
  int i = 0;

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 2) {
      if (null != data) {
        String event = data.getStringExtra("EVENT");
        previousValue[i] = event;
        i++;
        textViewTrainingEvents.setText(event);
      }
      for (int i = 0; previousValue.length(); i++){
//displaying all the previous values
        Log.e("someTag","value : "+previousValue[i]);
      }
    }
  }
}
