package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.MainActivity;
import com.parse.starter.R;

public class LoginActivity extends ActionBarActivity {
    private EditText mUsernameField;
    private EditText mPasswordField;
    private TextView mErrorField;
    private TextView mLoggedInField;
    private String mFromActivity; // the Activity that launched this activity if it was Raffle go back to Raffle upon login
    private ParseUser mCurrentUser;
    LinearLayout mLogInView;
    LinearLayout mLoggedInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCurrentUser = ParseUser.getCurrentUser();
        mLogInView = (LinearLayout) findViewById(R.id.logInView);
        mLoggedInView = (LinearLayout) findViewById(R.id.loggedInView);
        if (mCurrentUser == null) {
            mLoggedInView.setVisibility(LinearLayout.GONE);
            mLogInView.setVisibility(View.VISIBLE);
            mLogInView.invalidate();
            mLoggedInView.invalidate();
            mUsernameField = (EditText) findViewById(R.id.login_username);
            mPasswordField = (EditText) findViewById(R.id.login_password);
            mErrorField = (TextView) findViewById(R.id.error_messages);
        } else {
            mLogInView.setVisibility(View.GONE);
            mLoggedInView.setVisibility(View.VISIBLE);
            mLogInView.invalidate();
            mLoggedInView.invalidate();
            mLoggedInField = (TextView) findViewById(R.id.loggedinMessage);
            String loggedInString = "Logged in as: " + mCurrentUser.getUsername();
            mLoggedInField.setText(loggedInString);
        }
        mFromActivity = getIntent().getStringExtra("from");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void signIn(final View v){
        v.setEnabled(false);
        ParseUser.logInInBackground(mUsernameField.getText().toString(), mPasswordField.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    if (mFromActivity.compareTo(RaffleActivity.class.toString()) == 0) {
                        Intent intent = new Intent(LoginActivity.this, RaffleActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
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
                        case ParseException.OBJECT_NOT_FOUND:
                            mErrorField.setText("Sorry, those credentials were invalid.");
                            break;
                        default:
                            mErrorField.setText(e.getLocalizedMessage());
                            break;
                    }
                    v.setEnabled(true);
                }
            }
        });
    }

    public void showRegistration(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLogout(View v) {
        ParseUser.logOutInBackground(new LogOutCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    mLoggedInView.setVisibility(View.GONE);
                    mLogInView.setVisibility(View.VISIBLE);
                } else {
                    mErrorField.setText("Sorry, problem loggin out.");
                }
            }
        });

    }
}
