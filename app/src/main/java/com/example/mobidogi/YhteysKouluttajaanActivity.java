package com.example.mobidogi;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;
import static com.example.mobidogi.PreferenceHelper.setSendVideo;

public class YhteysKouluttajaanActivity extends AppCompatActivity implements PurchasesUpdatedListener {
  private static final String TAG = "YhteysKouluttajaanActivity";

  static final String ITEM_SKU_LAHETYS = "mobidogi.videonlahetys";

  private String mLahetysPrice;
  private SharedPreferences mSharedPreferences;

  private BillingClient mBillingClient;

  private static int VIDEO_REQUEST = 101;
  private Uri videoUri = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_yhteys_kouluttajaan);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    mBillingClient = BillingClient.newBuilder(YhteysKouluttajaanActivity.this).setListener(this).build();
    mBillingClient.startConnection(new BillingClientStateListener() {
      @Override
      public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
        if (billingResponseCode == BillingClient.BillingResponse.OK) {

          List skuList = new ArrayList<>();
          skuList.add(ITEM_SKU_LAHETYS);
          SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
          params.setSkusList(skuList).setType(INAPP);
          mBillingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {

              if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                for (Object skuDetailsObject : skuDetailsList) {
                  SkuDetails skuDetails = (SkuDetails) skuDetailsObject;
                  String sku = skuDetails.getSku();
                  String price = skuDetails.getPrice();
                  if (ITEM_SKU_LAHETYS.equals(sku)) {
                    mLahetysPrice = price;
                  }
                }
              }
            }
          });
        }
      }

      @Override
      public void onBillingServiceDisconnected() {

        Toast.makeText(YhteysKouluttajaanActivity.this, getResources().getString(R.string.billing_connection_failed), Toast.LENGTH_SHORT);
      }
    });
  }

  public void captureVideo(View view) {
    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

    if (videoIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(videoIntent, VIDEO_REQUEST);
    }
  }

  public void playVideo(View view) {
    Intent playIntent = new Intent(this, VideoPlayActivity.class);
    playIntent.putExtra("videoUri", videoUri.toString());
    startActivity(playIntent);
  }

  public void shareVideo(View view) {

    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
      .setSku(ITEM_SKU_LAHETYS)
      .setType(INAPP)
      .build();

    int responseCode = mBillingClient.launchBillingFlow(YhteysKouluttajaanActivity.this, flowParams);

    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mobidogisovellus@gmail.com", null));
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ongelmani");
    emailIntent.putExtra(Intent.EXTRA_TEXT, "Tarvitsisin apua seuraavaan ongelmaan");
    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(videoUri.toString()));
    if (emailIntent.resolveActivity(getPackageManager()) != null) {
      startActivity(emailIntent);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    if(requestCode==VIDEO_REQUEST && resultCode==RESULT_OK)
    {
      videoUri = data.getData();
    }
  }

  @Override
  public void onPurchasesUpdated(int responseCode, @Nullable List<com.android.billingclient.api.Purchase> purchases) {
    if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
      for (Purchase purchase : purchases) {
        handlePurchases(purchase);
        }
      } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
      Log.d(TAG, "lahetysmaksu peruttu" + responseCode);
    } else if (responseCode == BillingClient.BillingResponse.ITEM_ALREADY_OWNED) {
      mSharedPreferences.edit().putBoolean(getResources().getString(R.string.ostettu), true).commit();
      setSendVideo(true);
    } else {
      Log.d(TAG, "muu ostos" + responseCode);
    }
  }

  private void handlePurchases(Purchase purchase) {
   if (purchase.getSku().equals(ITEM_SKU_LAHETYS)) {
     mSharedPreferences.edit().putBoolean(getResources().getString(R.string.title_dashboard), true).commit();
     setSendVideo(true);
   }
    }
  }

