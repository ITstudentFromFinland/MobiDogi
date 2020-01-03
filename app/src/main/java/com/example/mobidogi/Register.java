package com.example.mobidogi;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

  EditText etName;
  EditText etAge;
  EditText etUsername;
  EditText etPassword;
  EditText etDogName;
  EditText etRotu;

  Button bRegister;

  UsersDBHelper usersDbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    usersDbHelper = new UsersDBHelper(this);
    initViews();
    bRegister.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        if (validate()) {
          String username = etUsername.getText().toString();
          String password = etPassword.getText().toString();

          if (!usersDbHelper.isEmailExists(username)) {

            usersDbHelper.addUser(new User(null, null, username, password));
            Snackbar.make(bRegister, "Tunnukset luotu onnistuneesti. Voit kirjautua.", Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                finish();
              }
            }, Snackbar.LENGTH_LONG);
          } else {
            Snackbar.make(bRegister, "Tämä sähköposti on jo käytössä ", Snackbar.LENGTH_LONG).show();
          }
        }
      }
    });
  }

  private void initViews() {

    etName = (EditText) findViewById(R.id.etName);
    etUsername = (EditText) findViewById(R.id.etUsername);
    etPassword = (EditText) findViewById(R.id.etPassword);
    etAge = (EditText) findViewById(R.id.etAge);
    etDogName = (EditText) findViewById(R.id.etDogName);
    etRotu = (EditText) findViewById(R.id.etRotu);
    bRegister = (Button) findViewById(R.id.bRegister);
  }

  public boolean validate() {
    boolean valid = false;

    String name = etName.getText().toString();
    int age = etAge.getText().toString();
    String email = etUsername.getText().toString();
    String password = etPassword.getText().toString();
    String dogName = etDogName.getText().toString();
    String rotu = etRotu.getText().toString();
  }
}
}

  TextView tvManualLink = findViewById(R.id.tvManualLink);
  TextView tvTrainerInfoLink = findViewById(R.id.tvTrainerInfoLink);
  TextView tvLogin = findViewById(R.id.tvLogin);

  tvManualLink.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

  Intent intManual = new Intent(Register.this, Kayttoehdot.class);
  startActivity(intManual);
  }
  });

  tvTrainerInfoLink.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
  Intent intInfo = new Intent(Register.this, TrainerInfo.class);
  startActivity(intInfo);
  }
  });

  tvLogin.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
  Intent intLogin = new Intent(Register.this, Login.class);
  startActivity(intLogin);
  }
  });
  }
  }
