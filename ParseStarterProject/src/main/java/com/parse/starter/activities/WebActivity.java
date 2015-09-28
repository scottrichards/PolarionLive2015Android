package com.parse.starter.activities;

// import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.parse.starter.R;

public class WebActivity extends Activity {
    public static final String ARG_URL_PARAM = "urlParam";
    public static final String ARG_TITLE_PARAM = "titleParam";

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url = getIntent().getStringExtra(ARG_URL_PARAM);
        String title = getIntent().getStringExtra(ARG_TITLE_PARAM);
        if (title != null && title.length() > 0)
            setTitle(title);
        webView = (WebView)findViewById(R.id.webView);
        if (url != null && url.length() > 0 )
            webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
