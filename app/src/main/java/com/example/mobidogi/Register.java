package com.example.mobidogi;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

  EditText etUsername;
  EditText etPassword;

  Button bRegister;

  UsersDBHelper usersDbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    usersDbHelper = new UsersDBHelper(this);
    initViews();

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

    etUsername = (EditText) findViewById(R.id.etUsername);
    etPassword = (EditText) findViewById(R.id.etPassword);
    bRegister = (Button) findViewById(R.id.bRegister);
  }

  public boolean validate() {
    boolean valid = false;

    String email = etUsername.getText().toString();
    String password = etPassword.getText().toString();

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      valid = false;
      Snackbar.make(bRegister, "Anna pätevä salasana", Snackbar.LENGTH_SHORT).show();
    } else {
      valid = true;
      Snackbar.make(bRegister, "null", Snackbar.LENGTH_INDEFINITE);
    }

    if (password.isEmpty()) {
      valid = false;
      Snackbar.make(bRegister, "Anna pätevä salasana", Snackbar.LENGTH_SHORT);
    } else {
      if (password.length() > 5) {
        valid = true;
        Snackbar.make(bRegister, "null", Snackbar.LENGTH_INDEFINITE);
      } else {
        valid = false;
        Snackbar.make(bRegister, "Salasana on liian lyhyt", Snackbar.LENGTH_SHORT);
      }
    }

    return valid;
  }
}
