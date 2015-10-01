package com.polarion.starter.activities;

// import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebActivity extends ActionBarActivity {
    public static final String ARG_URL_PARAM = "urlParam";
    public static final String ARG_TITLE_PARAM = "titleParam";

    private WebView webView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.polarion.starter.R.layout.activity_web);

        String url = getIntent().getStringExtra(ARG_URL_PARAM);
        String title = getIntent().getStringExtra(ARG_TITLE_PARAM);
//        if (title != null && title.length() > 0)
//            setTitle(title);

        // setup the Toolbar
        mToolbar = (Toolbar) findViewById(com.polarion.starter.R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            setSupportActionBar(mToolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webView = (WebView)findViewById(com.polarion.starter.R.id.webView);
        if (url != null && url.length() > 0 )
            webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:     // Going Back Finish this activity to go back to the Agenda
                Log.d("AgendaDetailsActivity", "Go Home");
                finish();
                return true;
            case com.polarion.starter.R.id.action_settings:

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
