package com.example.mobidogi;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

  private static final int REQUEST_READ_CONTACTS = 0;

  private static final String[] DUMMY_CREDENTIALS = new String[] {
  "foo@example.com:hello", "bar@example.com:world"
  };

  private EditText mPasswordView, mEmailView;
  private View mProgressView;
  private View mLoginFormView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mEmailView = (EditText) findViewById(R.id.txtUserName);
    mPasswordView = (EditText) findViewById(R.id.txtPassword);

    Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String Email = mEmailView.getText().toString();
        String Pwd = mPasswordView.getText().toString();
        if(Email.equalsIgnoreCase("leena.palsio@outlook.com") && Pwd.equals("pam1vpe628dug")) {
          Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
          startActivity(MainIntent);
          Toast.makeText(LoginActivity.this, "Olet kirjautunut sisään", Toast.LENGTH_LONG).show();
        }else
        {
          Toast.makeText(LoginActivity.this, "Sähköpostissa tai salasanassa on virhe", Toast.LENGTH_LONG).show();
        }
      }
    });

    mLoginFormView = findViewById(R.id.login_form);
    mProgressView= findViewById(R.id.login_progress);
  }
}
