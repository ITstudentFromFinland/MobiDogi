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

public class HairitsevaKaytosValikko extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hairitseva_kaytos_valikko);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(HairitsevaKaytosValikko.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(HairitsevaKaytosValikko.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            break;
        }
        return false;
      }
    });

    Button LuentoHairitsevaKaytos= findViewById(R.id.simpleImageViewArsyttavaKaytosLuento);
    Button Aktivointivinkkeja= findViewById(R.id.simpleImageViewAktivointiVinkkeja);
    Button PoisKouluttaminen= findViewById(R.id.simpleImageViewPoisKouluttaminen);

    LuentoHairitsevaKaytos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intHK = new Intent(HairitsevaKaytosValikko.this, LuentoHairitsevakaytos.class);
        startActivity(intHK);
      }
    });

    Aktivointivinkkeja.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intAV = new Intent(HairitsevaKaytosValikko.this, AktivointiVinkkeja.class);
        startActivity(intAV);
      }
    });

    PoisKouluttaminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intPK = new Intent(HairitsevaKaytosValikko.this, PoisKouluttaminen.class);
        startActivity(intPK);
      }
    });
  }
}
