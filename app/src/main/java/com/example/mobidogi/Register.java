package com.example.mobidogi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

  Button bRegister;
  EditText etName, etAge, etUsername, etPassword, etDogName, etRotu, etDogAge;
  TextView tvManualLink, tvTrainerInfoLink;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    etName = (EditText)findViewById(R.id.etName);
    etAge = (EditText)findViewById(R.id.etAge);
    etUsername = (EditText)findViewById(R.id.etUsername);
    etPassword = (EditText)findViewById(R.id.etPassword);
    etDogName = (EditText)findViewById(R.id.etDogName);
    etRotu = (EditText)findViewById(R.id.etRotu);
    etDogAge = (EditText)findViewById(R.id.etDogAge);
    bRegister = (Button)findViewById(R.id.bRegister);
    tvManualLink = (TextView)findViewById(R.id.tvManualLink);
    tvTrainerInfoLink = (TextView)findViewById(R.id.tvTrainerInfoLink);

    bRegister.setOnClickListener(this);
    tvManualLink.setOnClickListener(this);
    tvTrainerInfoLink.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch(view.getId()) {
      case R.id.bRegister:


        break;

      case R.id.tvManualLink:


        break;

      case R.id.tvTrainerInfoLink:

        break;
    }
  }
}
