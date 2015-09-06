package com.parse.starter.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.MainActivity;
import com.parse.starter.R;
import com.parse.starter.utility.URLService;

public class RaffleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            Intent loginIntent = new Intent(this,LoginActivity.class);
            loginIntent.putExtra("from",RaffleActivity.class.toString());
            startActivity(loginIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_raffle, menu);
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

    public void displayAlert(int messageId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(messageId)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onEnter(View view)
    {
        EditText testimonialEditText = (EditText) findViewById(R.id.testimonialText);
        CheckBox marketingUsage = (CheckBox)findViewById(R.id.marketingUsage);
        String testimonialText = testimonialEditText.getText().toString();
        if (testimonialText.length() == 0)
            displayAlert(R.string.provide_testimonial);
        else if (!marketingUsage.isChecked()) {
            displayAlert(R.string.check_marketing_usage);
        } else {
            ParseObject testimonial = new ParseObject("Testimonial");
            ParseUser currentUser = ParseUser.getCurrentUser();
            testimonial.put("user",currentUser);

            testimonial.put("testimonial", testimonialText);
            testimonial.saveEventually();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void openURL(String url)
    {
        Intent webIntent = new Intent(this,WebActivity.class);
        webIntent.putExtra("url",url);
        startActivity(webIntent);

    }

    public void onSampleTestimonial(View view)
    {
//        openURL(URLService.buildUrl("testimonial.html"));
        Intent webIntent = new Intent(this,WebActivity.class);
        webIntent.putExtra("url",URLService.buildUrl("testimonial.html"));
        webIntent.putExtra("title","Sample Testimonials");
        startActivity(webIntent);
    }

    public void onRules(View view)
    {
//        openURL(URLService.buildUrl("rules.html"));
        Intent webIntent = new Intent(this,WebActivity.class);
        webIntent.putExtra("url",URLService.buildUrl("rules.html"));
        webIntent.putExtra("title","Raffle Rules");
        startActivity(webIntent);
    }
}
