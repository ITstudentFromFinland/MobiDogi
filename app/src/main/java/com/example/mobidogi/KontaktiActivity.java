package com.example.mobidogi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class KontaktiActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_kontakti);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

    PDFView pdfView = findViewById(R.id.pdfView);
    pdfView.fromAsset("kontakti.pdf")
      .enableSwipe(true) // allows to block changing pages using swipe
      .swipeHorizontal(false)
      .enableDoubletap(true)
      .defaultPage(0).enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
      .password(null)
      .scrollHandle(null)
      .enableAntialiasing(true) // improve rendering a little bit on low-res screens
      // spacing between pages in dp. To define spacing color, set view background
      .spacing(1)
      .pageFitPolicy(FitPolicy.BOTH) // mode to fit pages in the view
      .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
      .nightMode(false) // toggle night mode
      .load();
  }
}
