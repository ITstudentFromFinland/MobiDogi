package com.example.mobidogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HihnanKirousActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hihnan_kirous);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(HihnanKirousActivity.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(HihnanKirousActivity.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            break;
        }
        return false;
      }
    });

    final Button Button = findViewById(R.id.simpleImageViewHihnanKirous);
    Button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(HihnanKirousActivity.this, LenkkeilyValikkoActivity.class));
      }

    });
  }
}
