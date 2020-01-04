package com.example.mobidogi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

  EditText etUsername;
  EditText etPassword;

  TextInputLayout textInputLayoutEmail;
  TextInputLayout textInputLayoutPassword;

  Button bLogin;


  SqliteHelper sqliteHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    sqliteHelper = new SqliteHelper(this);
    initViews();

    bLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (validate()) {

          //Get values from EditText fields
          String Email = etUsername.getText().toString();
          String Password = etPassword.getText().toString();

          //Authenticate user
          User currentUser = sqliteHelper.Authenticate(new User(null, Email, Password));

          //Check Authentication is successful or not
          if (currentUser != null) {
            Snackbar.make(bLogin, "Sisään kirjauduttu onnistuneesti", Snackbar.LENGTH_LONG).show();

            Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        finish();
          } else {

            //User Logged in Failed
            Snackbar.make(bLogin, "Sisään kirjautuminen epäonnistui, yritä uudelleen", Snackbar.LENGTH_LONG).show();

          }
        }
      }
    });

  TextView tvManualLink = findViewById(R.id.tvManualLink);
  TextView tvTrainerInfoLink = findViewById(R.id.tvTrainerInfoLink);

    tvManualLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      Intent intManual = new Intent(Login.this, Kayttoehdot.class);
      startActivity(intManual);
    }
  });

    tvTrainerInfoLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      Intent intInfo = new Intent(Login.this, TrainerInfo.class);
      startActivity(intInfo);
    }
  });
}

  private void initViews() {

    etUsername = (EditText) findViewById(R.id.etUsername);
    etPassword = (EditText) findViewById(R.id.etPassword);
    textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
    textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
    bLogin = (Button) findViewById(R.id.bLogin);

  }

  @SuppressWarnings("deprecation")
  public static Spanned fromHtml(String html) {
    Spanned result;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
    } else {
      result = Html.fromHtml(html);
    }
    return result;
  }

  public boolean validate() {
    boolean valid = false;

    //Get values from EditText fields
    String Email = etUsername.getText().toString();
    String Password = etPassword.getText().toString();

    //Handling validation for Email field
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
      valid = false;
      textInputLayoutEmail.setError("Please enter valid email!");
    } else {
      valid = true;
      textInputLayoutEmail.setError(null);
    }

    if (Password.isEmpty()) {
      valid = false;
      textInputLayoutPassword.setError("Please enter valid password!");
    } else {
      if (Password.length() > 5) {
        valid = true;
        textInputLayoutPassword.setError(null);
      } else {
        valid = false;
        textInputLayoutPassword.setError("Password is to short!");
      }
    }

    return valid;
  }
}
