package com.example.mobidogi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

public class YhteysKouluttajaanActivity extends AppCompatActivity {
  private static int VIDEO_REQUEST = 101;
  private Uri videoUri = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_yhteys_kouluttajaan);
  }
  public void captureVideo(View view)
  {
    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

    if(videoIntent.resolveActivity(getPackageManager()) !=null)
    {
      startActivityForResult(videoIntent, VIDEO_REQUEST);
    }
  }

  public void playVideo(View view)
  {
    Intent playIntent = new Intent(this, VideoPlayActivity.class);
    playIntent.putExtra("videoUri", videoUri.toString());
    startActivity(playIntent);
  }

  public void shareVideo(View view) {
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
}
