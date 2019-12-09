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


public class YksinoloValikkoActivity extends AppCompatActivity {
  TextView mTextMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_yksinolo_valikko);


    BottomNavigationView navView = findViewById(R.id.nav_view);

    mTextMessage = findViewById(R.id.message);

    navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


                                                  @Override
                                                  public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                                    switch (item.getItemId()) {
                                                      case R.id.navigation_home:
                                                        Intent e = new Intent(YksinoloValikkoActivity.this, MainActivity.class);
                                                        startActivity(e);
                                                        break;

                                                      case R.id.navigation_dashboard:
                                                        Intent y = new Intent(YksinoloValikkoActivity.this, YhteysKouluttajaanActivity.class);
                                                        startActivity(y);
                                                        break;

                                                      case R.id.navigation_notifications:
                                                        break;
                                                    }
                                                    return false;
                                                  }
                                                }
    );



    Button LuentoYksinolo= findViewById(R.id.simpleImageViewYksinoloLuento);
    Button Luopuminen= findViewById(R.id.simpleImageViewYksinoloLuopuminen);
    Button AlustalleMeneminen= findViewById(R.id.SimpleImageViewMeneminenAlustalle);
    Button HarjoitusYksinolo=findViewById(R.id.simpleImageViewYksinolonkoulutus);

    LuentoYksinolo.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intLY = new Intent(YksinoloValikkoActivity.this, YksinoloLuentoPDF.class);
        startActivity(intLY);
      }
    });

    Luopuminen.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intLUOP = new Intent(YksinoloValikkoActivity.this, OmistajastapoisActivity.class);
        startActivity(intLUOP);
      }
    });

    AlustalleMeneminen.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intALUME = new Intent(YksinoloValikkoActivity.this, KohdentaminenActivity.class);
        startActivity(intALUME);
      }
    });

    HarjoitusYksinolo.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        Intent intHARYK = new Intent(YksinoloValikkoActivity.this, YksinolonkoulutusPDF.class);
        startActivity(intHARYK);
      }
    });
  }
}
