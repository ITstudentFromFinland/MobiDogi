package com.example.mobidogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    final EditText etUsername = (EditText) findViewById(R.id.etUsername);
    final EditText etPassword = (EditText) findViewById(R.id.etPassword);
    final Button bLogin = (Button) findViewById(R.id.bLogin);

    bLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonResponse = new JSONObject(response);
              boolean success = jsonResponse.getBoolean("success");

              if (success) {
                  Intent intent = new Intent(Login.this, MainActivity.class);

                  Login.this.startActivity(intent);

              } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Kirjautuminen epäonnistui")
                  .setNegativeButton("Yritä uudelleen", null)
                  .create()
                  .show();
              }

            } catch (JSONException e) {
              e.printStackTrace();
            }

          }
        };

        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        queue.add(loginRequest);
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
}
