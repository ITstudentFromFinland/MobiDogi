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

public class HoitotoimenpiteetValikko extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hoitotoimenpiteet_valikko);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(HoitotoimenpiteetValikko.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(HoitotoimenpiteetValikko.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            break;
        }
        return false;
      }
    });

    Button LuentoHoitotoimenpiteet= findViewById(R.id.simpleImageViewHoitotoimenpiteetLuento);
    Button Rauhoittuminen= findViewById(R.id.simpleImageViewRauhoittuminen);
    Button Alustallemeno= findViewById(R.id.simpleImageViewKohdentaminen);
    Button OhjeHoitotoimenpiteet= findViewById(R.id.simpleImageViewHoitotoimenpiteetOhje);

    LuentoHoitotoimenpiteet.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intL = new Intent(HoitotoimenpiteetValikko.this,LuentoHoitotoimenpiteet.class);
        startActivity(intL);
      }
    });

    Rauhoittuminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intR = new Intent(HoitotoimenpiteetValikko.this, RauhoittuminenActivity.class);
        startActivity(intR);
      }
    });

    Alustallemeno.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intAM = new Intent(HoitotoimenpiteetValikko.this, KohdentaminenActivity.class);
        startActivity(intAM);
      }
    });

    OhjeHoitotoimenpiteet.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intOH = new Intent(HoitotoimenpiteetValikko.this, HoitotoimenpiteetOhjePDF.class);
        startActivity(intOH);
      }
    });
  }
}
