package com.example.mobidogi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;
import static com.example.mobidogi.PreferenceHelper.setLenkkeily;
import static com.example.mobidogi.PreferenceHelper.setLuoksetulo;
import static com.example.mobidogi.PreferenceHelper.setHairitsevakaytos;
import static com.example.mobidogi.PreferenceHelper.setHoitotoimenpiteet;
import static com.example.mobidogi.PreferenceHelper.setYksinolo;


public class MainActivity extends AppCompatActivity implements PurchasesUpdatedListener {
  TextView mTextMessage;

  private static final String TAG = "MainActivity";

  static final String ITEM_SKU_LENKKEILY = "mobidogi.lenkkeily";
  static final String ITEM_SKU_LUOKSETULO = "mobidogi.luoksetulo";
  static final String ITEM_SKU_HAIRITSEVAKAYTOS = "mobidogi.hairitsevakaytos";
  static final String ITEM_SKU_HOITOTOIMENPITEET = "mobidogi.hoitotoimenpiteet";
  static final String ITEM_SKU_YKSINOLO = "mobidogi.yksinolo";

  private String mLenkkeilyPrice;
  private String mLuoksetuloPrice;
  private String mHairitsevakaytosPrice;
  private String mHoitotoimenpiteetPrice;
  private String mYksinoloPrice;
  private SharedPreferences mSharedPreferences;

  private BillingClient mBillingClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    mBillingClient = BillingClient.newBuilder(MainActivity.this).setListener(this).build();
    mBillingClient.startConnection(new BillingClientStateListener() {
      @Override
      public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
        if (billingResponseCode == BillingClient.BillingResponse.OK) {

          List skuList = new ArrayList<>();
          skuList.add(ITEM_SKU_LENKKEILY);
          skuList.add(ITEM_SKU_LUOKSETULO);
          skuList.add(ITEM_SKU_HAIRITSEVAKAYTOS);
          skuList.add(ITEM_SKU_HOITOTOIMENPITEET);
          skuList.add(ITEM_SKU_YKSINOLO);
          SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
          params.setSkusList(skuList).setType(INAPP);
          mBillingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List skuDetailsList) {
              //process the result.

              if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                for (Object skuDetailsObject : skuDetailsList) {
                  SkuDetails skuDetails = (SkuDetails) skuDetailsObject;
                  String sku = skuDetails.getSku();
                  String price = skuDetails.getPrice();
                  if (ITEM_SKU_LENKKEILY.equals(sku)) {
                    mLenkkeilyPrice = price;
                  } else if (ITEM_SKU_LUOKSETULO.equals(sku)) {
                    mLuoksetuloPrice = price;
                  } else if (ITEM_SKU_HAIRITSEVAKAYTOS.equals(sku)) {
                    mHairitsevakaytosPrice = price;
                  } else if (ITEM_SKU_HOITOTOIMENPITEET.equals(sku)) {
                    mHoitotoimenpiteetPrice = price;
                  } else if (ITEM_SKU_YKSINOLO.equals(sku)) {
                    mYksinoloPrice = price;
                  }
                }
              }
            }
          });
        }
      }

      @Override
      public void onBillingServiceDisconnected() {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.billing_connection_failed), Toast.LENGTH_SHORT);

      }
    });

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

    TextView tvTrainerInfo = findViewById(R.id.tvTrainerInfoLink);
    Button MitenKoiraOppii = findViewById(R.id.simpleImageViewMitenKoiraOppii);
    Button Lenkkeily = findViewById(R.id.simpleImageViewLenkkeily);

    tvTrainerInfo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intInfo = new Intent(MainActivity.this, TrainerInfo.class);
        startActivity(intInfo);
      }
    });


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

        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
          .setSku(ITEM_SKU_LENKKEILY)
          .setType(INAPP)
          .build();

        int responseCode = mBillingClient.launchBillingFlow(MainActivity.this, flowParams);

        if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
          Intent lenkkeilypaid = new Intent(MainActivity.this, LenkkeilyValikkoActivity.class);
          startActivity(lenkkeilypaid);
      }}
    });

        Button Luoksetulo = findViewById(R.id.simpleImageViewLuoksetulo);

        Luoksetulo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
              .setSku(ITEM_SKU_LUOKSETULO)
              .setType(INAPP)
              .build();

            int responseCode = mBillingClient.launchBillingFlow(MainActivity.this, flowParams);

            if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
              Intent luoksepaid = new Intent(MainActivity.this, LuoksetuloValikkoActivity.class);
              startActivity(luoksepaid);
          }}
        });

        Button HairitsevaKaytos = findViewById(R.id.simpleImageViewHairitsevaKaytos);

        HairitsevaKaytos.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
              .setSku(ITEM_SKU_HAIRITSEVAKAYTOS)
              .setType(INAPP)
              .build();

            int responseCode = mBillingClient.launchBillingFlow(MainActivity.this, flowParams);

            if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
              Intent hairitsevapaid = new Intent(MainActivity.this, HairitsevaKaytosValikko.class);
              startActivity(hairitsevapaid);
            }
          }
        });

        Button Hoitotoimenpiteet = findViewById(R.id.simpleImageViewHoitotoimenpiteet);

        Hoitotoimenpiteet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
              .setSku(ITEM_SKU_HOITOTOIMENPITEET)
              .setType(INAPP)
              .build();

            int responseCode = mBillingClient.launchBillingFlow(MainActivity.this, flowParams);

            if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
              Intent hoitopaid = new Intent(MainActivity.this, HoitotoimenpiteetValikko.class);
              startActivity(hoitopaid);
            }
          }
        });

        Button Yksinolo = findViewById(R.id.simpleImageViewYksinolo);

        Yksinolo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){

      BillingFlowParams flowParams = BillingFlowParams.newBuilder()
        .setSku(ITEM_SKU_YKSINOLO)
        .setType(INAPP)
        .build();

      int responseCode = mBillingClient.launchBillingFlow(MainActivity.this, flowParams);

            if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
              Intent yksinolopaid = new Intent(MainActivity.this, YksinoloValikkoActivity.class);
              startActivity(yksinolopaid);
            }
        }});
  }

      private void handlePurchases(Purchase purchase) {
        if (purchase.getSku().equals(ITEM_SKU_LENKKEILY)) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_lenkkeily), true).commit();
          setLenkkeily(true);
          Intent lenkkeily = new Intent(MainActivity.this, LenkkeilyValikkoActivity.class);
          startActivity(lenkkeily);
        }
        else if (purchase.getSku().equals(ITEM_SKU_LUOKSETULO)) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_luoksetulo), true).commit();
          setLuoksetulo(true);
          Intent luoksetulo = new Intent(MainActivity.this, LuoksetuloValikkoActivity.class);
          startActivity(luoksetulo);
        }
        else if (purchase.getSku().equals(ITEM_SKU_HAIRITSEVAKAYTOS)) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_hairitseva_kaytos), true).commit();
          setHairitsevakaytos(true);
          Intent hairitseva = new Intent(MainActivity.this, HairitsevaKaytosValikko.class);
          startActivity(hairitseva);
        }
        else if (purchase.getSku().equals(ITEM_SKU_HOITOTOIMENPITEET)) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_hoitotoimenpiteet), true).commit();
          setHoitotoimenpiteet(true);
          Intent hoito = new Intent(MainActivity.this, HoitotoimenpiteetValikko.class);
          startActivity(hoito);
        } else if (purchase.getSku().equals(ITEM_SKU_YKSINOLO)) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_yksinolo), true).commit();
          setYksinolo(true);
          Intent yksin = new Intent(MainActivity.this, YksinoloValikkoActivity.class);
          startActivity(yksin);
        }
      }

      @Override
      public void onPurchasesUpdated(int responseCode, @Nullable List<com.android.billingclient.api.Purchase> purchases) {

        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
          for (Purchase purchase : purchases) {
            handlePurchases(purchase);
          }
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
          Log.d(TAG, "Ostos peruttu" + responseCode);
        } else if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
          mSharedPreferences.edit().putBoolean(getResources().getString(R.string.ostettu), true).commit();
          setLenkkeily(true);
        } else {
          Log.d(TAG, "muu ostos" + responseCode);

          }
        }
      }
