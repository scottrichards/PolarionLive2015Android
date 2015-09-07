package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.MainActivity;
import com.parse.starter.R;

public class RegisterActivity extends ActionBarActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mEMailField;
    private EditText mCompanyField;
    private EditText mTitleField;
    private TextView mErrorField;
    private EditText mFullNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameField = (EditText) findViewById(R.id.register_username);
        mPasswordField = (EditText) findViewById(R.id.register_password);
        mEMailField = (EditText) findViewById(R.id.email);
        mCompanyField= (EditText) findViewById(R.id.company);
        mTitleField = (EditText) findViewById(R.id.title);
        mErrorField = (TextView) findViewById(R.id.error_messages);
        mFullNameField = (EditText) findViewById(R.id.fullName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    public void register(final View v){
        if(mUsernameField.getText().length() == 0 || mPasswordField.getText().length() == 0)
            return;

        v.setEnabled(false);
        ParseUser user = new ParseUser();
        user.setUsername(mUsernameField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());
        user.setEmail(mEMailField.getText().toString());
        user.put("company",mCompanyField.getText().toString());
        user.put("title",mTitleField.getText().toString());
        user.put("fullName",mFullNameField.getText().toString());
        mErrorField.setText("");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    switch (e.getCode()) {
                        case ParseException.USERNAME_TAKEN:
                            mErrorField.setText("Sorry, this username has already been taken.");
                            break;
                        case ParseException.USERNAME_MISSING:
                            mErrorField.setText("Sorry, you must supply a username to register.");
                            break;
                        case ParseException.PASSWORD_MISSING:
                            mErrorField.setText("Sorry, you must supply a password to register.");
                            break;
                        default:
                            mErrorField.setText(e.getLocalizedMessage());
                    }
                    v.setEnabled(true);
                }
            }
        });
    }

    public void showLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
