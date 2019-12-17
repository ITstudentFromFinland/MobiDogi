package com.example.mobidogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

  Button bLogin;
  EditText etUsername, etPassword;
  TextView tvManualLink, tvTrainerInfoLink;

  UserLocalStore userLocalStore;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    etUsername = (EditText)findViewById(R.id.etUsername);
    etPassword = (EditText)findViewById(R.id.etPassword);
    bLogin = (Button)findViewById(R.id.bLogin);
    tvManualLink = (TextView)findViewById(R.id.tvManualLink);
    tvTrainerInfoLink = (TextView)findViewById(R.id.tvTrainerInfoLink);

    bLogin.setOnClickListener(this);
    tvManualLink.setOnClickListener(this);
    tvTrainerInfoLink.setOnClickListener(this);

    userLocalStore = new UserLocalStore(this);
  }
  @Override
  public void onClick(View view) {
    switch(view.getId()) {
      case R.id.bLogin:
        User user = new User(null, null);

        userLocalStore.storeUserData(user);
        userLocalStore.setUserLoggedIn(true);

        break;

      case R.id.tvManualLink:

        startActivity(new Intent(this, Kayttoehdot.class));

        break;

      case R.id.tvTrainerInfoLink:

        startActivity(new Intent(this, TrainerInfo.class));

        break;
    }
  }
}
