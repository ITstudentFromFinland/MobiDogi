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

public class LuoksetuloValikkoActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_luoksetulo_valikko);

    BottomNavigationView navView = findViewById(R.id.nav_view);
    mTextMessage = findViewById(R.id.message);
    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navigation_home:
            Intent e = new Intent(LuoksetuloValikkoActivity.this, MainActivity.class);
            startActivity(e);
            break;

            case R.id.navigation_dashboard:
              Intent y = new Intent(LuoksetuloValikkoActivity.this, YhteysKouluttajaanActivity.class);
              startActivity(y);
              break;

              case R.id.navigation_notifications:
                break;
        }
        return false;
      }
    }
    );

    Button LuentoLuoksetulo= findViewById(R.id.simpleImageViewLuoksetuloLuento);
    Button Luoksetulonkoulutus= findViewById(R.id.simpleImageViewLuoksetulonKoulutus);
    Button LahellaPysyminen= findViewById(R.id.SimpleImageViewLahellaPysyminen);

    LuentoLuoksetulo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent int51 = new Intent(new Intent(LuoksetuloValikkoActivity.this, LuentoLuoksetuloActivity.class));
        startActivity(int51);
      }
    });

    Luoksetulonkoulutus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent int52 = new Intent(new Intent(LuoksetuloValikkoActivity.this, LuoksetulonkoulutusPdfActivity.class));
        startActivity(int52);
      }
    });

    LahellaPysyminen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Intent int53 = new Intent(new Intent(LuoksetuloValikkoActivity.this, LahellapysyminenActivity.class));
        startActivity(int53);
      }
    });
  }
}
