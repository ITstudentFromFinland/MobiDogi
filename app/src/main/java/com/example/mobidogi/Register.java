package com.example.mobidogi;

import android.content.Intent;
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

public class Register extends AppCompatActivity implements View.OnClickListener {

  Button bRegister;
  EditText etName, etAge, etUsername, etPassword, etDogName, etRotu;
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
        String name = etName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String dogname = etDogName.getText().toString();
        String rotu = etRotu.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());

        Response.Listener<String> responseListener = new Response.Listener<String>(){

          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonResponse = new JSONObject(response);
              boolean success = jsonResponse.getBoolean("success");

              if (success) {
                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
              } else{
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setMessage("Rekisteröityminen epäonnistui")
                  .setNegativeButton("Yritä uudesaan", null)
                  .create()
                  .show();
              }

            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, dogname, rotu, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);

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
