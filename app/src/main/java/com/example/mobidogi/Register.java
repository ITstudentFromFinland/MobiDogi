package com.example.mobidogi;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

  EditText etUsername;
  EditText etPassword;

  TextInputLayout textInputLayoutUserName;
  TextInputLayout textInputLayoutEmail;
  TextInputLayout textInputLayoutPassword;

  Button bRegister;

  SqliteHelper sqliteHelper;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    sqliteHelper = new SqliteHelper(this);
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
          String Email = etUsername.getText().toString();
          String Password = etPassword.getText().toString();

          //Check in the database is there any user associated with  this email
          if (!sqliteHelper.isEmailExists(Email)) {

            //Email does not exist now add new user to database
            sqliteHelper.addUser(new User(null, Email, Password));
            Snackbar.make(bRegister, "Tunnukset luotu onnistuneesti! Voit kirjautua ", Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                finish();
              }
            }, Snackbar.LENGTH_LONG);
          }else {

            //Email exists with email input provided so show error user already exist
            Snackbar.make(bRegister, "Sähköposti on jo käytössä. Yritä uudelleen. ", Snackbar.LENGTH_LONG).show();
          }

        }
      }
    });
  }

  private void initViews() {

    etUsername = (EditText) findViewById(R.id.etUsername);
    etPassword = (EditText) findViewById(R.id.etPassword);
    textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
    textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
    bRegister = (Button) findViewById(R.id.bRegister);
  }

  public boolean validate() {
    boolean valid = false;

    //Get values from EditText fields
    String Email = etUsername.getText().toString();
    String Password = etPassword.getText().toString();

    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
      valid = false;
      textInputLayoutEmail.setError("Syötä voimassaoleva salasana");
    } else {
      valid = true;
      textInputLayoutEmail.setError(null);
    }

    if (Password.isEmpty()) {
      valid = false;
      textInputLayoutPassword.setError("Syötä voimassaoleva salasana!");
    } else {
      if (Password.length() > 5) {
        valid = true;
        textInputLayoutPassword.setError(null);
      } else {
        valid = false;
        textInputLayoutPassword.setError("Salasana on liian lyhyt!");
      }
    }


    return valid;
  }
}
