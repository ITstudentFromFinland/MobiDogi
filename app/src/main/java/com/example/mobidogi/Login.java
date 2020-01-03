package com.example.mobidogi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

  Button bLogin;


  UsersDBHelper usersDbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    usersDbHelper = new UsersDBHelper(this);
    initViews();

    bLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (validate()) {

          String Sahkoposti = etUsername.getText().toString();
          String Salasana = etPassword.getText().toString();

          User currentUser = usersDbHelper.Authenticate(new User(null, null, Sahkoposti, Salasana));

          if (currentUser != null) {
            Snackbar.make(bLogin, "Kirjautuminen onnistui", Snackbar.LENGTH_LONG).show();

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
          } else {
            Snackbar.make(bLogin, "Kirjautuminen epäonnistui, yritä uudelleen", Snackbar.LENGTH_LONG).show();
          }
        }
      }
    });
  }

  private void initViews() {

    etUsername = (EditText) findViewById(R.id.etUsername);
    etPassword = (EditText) findViewById(R.id.etPassword);
    bLogin = (Button) findViewById(R.id.bLogin);

  }

  @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
    Spanned result;
    if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
    } else {
      result = Html.fromHtml(html);
    }
    return result;
    }

    public boolean validate() {
      boolean valid = false;

      String Email = etUsername.getText().toString();
      String Password = etPassword.getText().toString();

      if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
        valid = false;
        Snackbar.make(bLogin, "Anna oikea sähköposti", Snackbar.LENGTH_SHORT).show();
      } else {
        valid = true;
        Snackbar.make(bLogin, null, Snackbar.LENGTH_INDEFINITE);
      }

      if (Password.isEmpty()) {
        valid = false;
        Snackbar.make(bLogin, "Anna oikea salasana", Snackbar.LENGTH_SHORT).show();
      } else {
        if (Password.length() > 5) {
          valid = true;
          Snackbar.make(bLogin, null, Snackbar.LENGTH_INDEFINITE);
        } else {
          valid = false;
          Snackbar.make(bLogin, "Salasana on liian lyhyt", Snackbar.LENGTH_SHORT).show();
        }
      }

      return valid;
  }


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
}
