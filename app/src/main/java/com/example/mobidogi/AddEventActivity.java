package com.example.mobidogi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddEventActivity extends AppCompatActivity {
  EditText editTextTrainingEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_event);

    editTextTrainingEvent = (EditText) findViewById(R.id.editTextTrainingEvent);
  }

  public void submitTrainingEvent(View view)
  {
    String message=editTextTrainingEvent.getText().toString();
    Intent intMessage = new Intent();

    intMessage.putExtra("EVENT", message);

    setResult(2, intMessage);
    finish();
  }
}
