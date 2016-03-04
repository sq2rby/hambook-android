package ch.tink.humhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    protected SharedPreferences sharedPreferences;
    private WebView myWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sharedPreferences = this.getSharedPreferences("share",MODE_PRIVATE);

        //check if a url is set and then decide which view can be loaded
        if(this.isUrlSet()){
            this.myWebView = (WebView) findViewById(R.id.webView);
            this.loadWebView();
        }
        else {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    private boolean isUrlSet(){
        String url = this.sharedPreferences.getString("url","");
        if(url == "")
            return false;
        else
            return true;
    }

    private void loadWebView(){
        this.url = this.sharedPreferences.getString("url","");

        //Enable Java
        this.myWebView.getSettings().setJavaScriptEnabled(true);
        //Set Client
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });
        //Load URL
        myWebView.loadUrl(this.url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());

        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
