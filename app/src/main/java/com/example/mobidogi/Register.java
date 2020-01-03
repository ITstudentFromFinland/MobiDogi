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

public class Register extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    final EditText etName = (EditText) findViewById(R.id.etName);
    final EditText etUsername = (EditText) findViewById(R.id.etUsername);
    final EditText etPassword = (EditText) findViewById(R.id.etPassword);
    final EditText etAge = (EditText) findViewById(R.id.etAge);
    final EditText etDogName = (EditText) findViewById(R.id.etDogName);
    final EditText etRotu = (EditText) findViewById(R.id.etRotu);

    final Button bRegister = (Button) findViewById(R.id.bRegister);

    bRegister.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        String name = etName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String dogname = etDogName.getText().toString();
        String rotu = etRotu.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {

          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
              boolean success = jsonObject.getBoolean("success");

              if (success) {
                Intent intent = new Intent(Register.this, Login.class);
                Register.this.startActivity(intent);
              } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setMessage("Rekisteröityminen epäonnistui")
                  .setNegativeButton("Yritä uudestaan", null)
                  .create()
                  .show();
              }

            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, username, password, age, dogname, rotu, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);
      }
    });

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
