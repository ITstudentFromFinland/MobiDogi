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

public class SecondActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(e);
            break;

          case R.id.navigation_dashboard:
            Intent y = new Intent(SecondActivity.this, YhteysKouluttajaanActivity.class);
            startActivity(y);
            break;

          case R.id.navigation_notifications:
            Intent tp = new Intent(SecondActivity.this, Treenipaivakirja.class);
            startActivity(tp);
            break;
        }
        return false;
      }
    });

    Button LuentoOppiminen = findViewById(R.id.simpleImageViewOppiminenLuento);
    Button Ehdollistaminen = findViewById(R.id.simpleImageViewEhdollistaminen);
    Button Kohdentaminen = findViewById(R.id.simpleImageViewKohdentaminen);
    Button Luopuminen = findViewById(R.id.simpleImageViewLuopuminen);
    Button Rauhoittuminen = findViewById(R.id.simpleImageViewRauhoittuminen);
    Button LahellaPysyminen = findViewById(R.id.SimpleImageViewLahellaPysyminen);

    LuentoOppiminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent intLO = new Intent(SecondActivity.this, LuentoOppiminenActivity.class);
        startActivity(intLO);
      }
    });

    Ehdollistaminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intEH = new Intent(SecondActivity.this, EhdollistaminenActivity.class);
        startActivity(intEH);
      }
    });

    Kohdentaminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intKOHD = new Intent(SecondActivity.this, KohdentaminenActivity.class);
        startActivity(intKOHD);
      }
    });

    Luopuminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intLUOP = new Intent(SecondActivity.this, OmistajastapoisActivity.class);
        startActivity(intLUOP);
      }
    });

    Rauhoittuminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intRAU = new Intent(SecondActivity.this, RauhoittuminenActivity.class);
        startActivity(intRAU);
      }
    });
    LahellaPysyminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intLP = new Intent(SecondActivity.this, LahellapysyminenActivity.class);
        startActivity(intLP);
      }

    });
  }
}
