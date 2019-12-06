package com.example.mobidogi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(MainActivity.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(MainActivity.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            Intent tp = new Intent(MainActivity.this, Treenipaivakirja.class);
            startActivity(tp);
            break;
        }
        return false;
      }
    });

    Button MitenKoiraOppii= findViewById(R.id.simpleImageViewMitenKoiraOppii);
    Button Lenkkeily= findViewById(R.id.simpleImageViewLenkkeily);
    Button Luoksetulo= findViewById(R.id.simpleImageViewLuoksetulo);
    Button HairitsevaKaytos= findViewById(R.id.simpleImageViewHairitsevaKaytos);
    Button Hoitotoimenpiteet= findViewById(R.id.simpleImageViewHoitotoimenpiteet);

    MitenKoiraOppii.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent int1 = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(int1);
      }
    });

    Lenkkeily.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int2 = new Intent(MainActivity.this, HihnanKirousActivity.class);
        startActivity(int2);
      }
    });

    Luoksetulo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int3 = new Intent(MainActivity.this, LuoksetuloValikkoActivity.class);
        startActivity(int3);
      }
    });

    HairitsevaKaytos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intHK = new Intent(MainActivity.this, HairitsevaKaytosValikko.class);
        startActivity(intHK);
      }
    });

    Hoitotoimenpiteet.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int4 = new Intent(MainActivity.this, HoitotoimenpiteetValikko.class);
        startActivity(int4);
      }
    });
  }
}
