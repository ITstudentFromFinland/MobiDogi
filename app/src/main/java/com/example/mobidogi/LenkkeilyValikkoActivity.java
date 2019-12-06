package com.example.mobidogi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LenkkeilyValikkoActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lenkkeily_valikko);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(LenkkeilyValikkoActivity.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(LenkkeilyValikkoActivity.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            Intent tp = new Intent(LenkkeilyValikkoActivity.this, Treenipaivakirja.class);
            startActivity(tp);
            break;
        }
        return false;
      }
    });

    Button UloslahtoRauhallisesti= findViewById(R.id.simpleImageViewUloslahto);
    Button HihnassaKauniisti= findViewById(R.id.simpleImageViewHihnassaKauniisti);
    Button Valineet= findViewById(R.id.simpleImageViewValineet);
    Button Kontakti= findViewById(R.id.simpleImageViewKontakti);
    Button Motivointi= findViewById(R.id.simpleImageViewMotivointi);
    Button Ohittaminen= findViewById(R.id.simpleImageViewOhittaminen);
    Button Kaupunkikavely= findViewById(R.id.simpleImageViewKaupunkikavely);

    UloslahtoRauhallisesti.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intUL = new Intent(LenkkeilyValikkoActivity.this, UloslahtorauhallisestiActivity.class);
        startActivity(intUL);
      }
    });

    HihnassaKauniisti.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intHKK = new Intent(LenkkeilyValikkoActivity.this, KauniistikavelyActivity.class);
        startActivity(intHKK);
      }
    });

    Valineet.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intVAL = new Intent(LenkkeilyValikkoActivity.this, ValineetActivity.class);
        startActivity(intVAL);
      }
    });

    Kontakti.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intKONT = new Intent(LenkkeilyValikkoActivity.this, KontaktiActivity.class);
        startActivity(intKONT);
      }
    });

    Motivointi.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intMOTI = new Intent(LenkkeilyValikkoActivity.this, MotivointiActivity.class);
        startActivity(intMOTI);
      }
    });

    Ohittaminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intOHI = new Intent(LenkkeilyValikkoActivity.this, OhittaminenActivity.class);
        startActivity(intOHI);
      }
    });

    Kaupunkikavely.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intKAUKA = new Intent(LenkkeilyValikkoActivity.this, KaupunkikavelyActivity.class);
        startActivity(intKAUKA);
      }
    });
  }
}
