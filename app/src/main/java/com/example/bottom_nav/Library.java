package com.example.bottom_nav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Library extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        webView= (WebView)findViewById(R.id.Liabray);

        //enable javascript
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //Initialize ProgressBar
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        // Set client for your WebView and load the URL as shown below
        webView.setWebViewClient(new WebBrowser());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);

                }

                progressBar.setProgress(progress);
                if(progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);

                }
                super.onProgressChanged(view, progress);
            }
        });
        webView.loadUrl("https://google.com");

    }
    public static class WebBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
