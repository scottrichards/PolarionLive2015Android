package com.parse.starter.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.MainActivity;
import com.parse.starter.R;
import com.parse.starter.activities.LoginActivity;
import com.parse.starter.activities.RaffleActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    View mView;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private TextView mErrorField;
    private TextView mLoggedInField;
    private ParseUser mCurrentUser;
    LinearLayout mLogInView;
    LinearLayout mLoggedInView;

    public static final String FROM_ACTIVITY = "fromActivity";

    private String fromActivity;

    private Thread.UncaughtExceptionHandler androidDefaultUEH;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fromActivity Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String fromActivity, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(FROM_ACTIVITY, fromActivity);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fromActivity = getArguments().getString(FROM_ACTIVITY);

        } else {
            fromActivity = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_login, container, false);

        mCurrentUser = ParseUser.getCurrentUser();
        mLogInView = (LinearLayout) mView.findViewById(R.id.logInView);
        mLoggedInView = (LinearLayout) mView.findViewById(R.id.loggedInView);
        if (mCurrentUser == null) {
            mLoggedInView.setVisibility(LinearLayout.GONE);
            mLogInView.setVisibility(View.VISIBLE);
            mLogInView.invalidate();
            mLoggedInView.invalidate();
            mUsernameField = (EditText) mView.findViewById(R.id.login_username);
            mPasswordField = (EditText) mView.findViewById(R.id.login_password);
            mErrorField = (TextView) mView.findViewById(R.id.error_messages);
        } else {
            mLogInView.setVisibility(View.GONE);
            mLoggedInView.setVisibility(View.VISIBLE);
            mLogInView.invalidate();
            mLoggedInView.invalidate();
            mLoggedInField = (TextView) mView.findViewById(R.id.loggedinMessage);
            String loggedInString = "Logged in as: " + mCurrentUser.getUsername();
            mLoggedInField.setText(loggedInString);
        }
        Button button = (Button)mView.findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signIn();
                String userName = mUsernameField.getText().toString();
                String password = mPasswordField.getText().toString();
                if (userName.length() == 0 || password.length() == 0) {
                    mErrorField.setText(getResources().getString(R.string.enter_username));
                    displayAlert(R.string.enter_username);
                } else {
                    ParseUser.logInInBackground(userName, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                if (fromActivity != null) {
                                    mListener.onStartActivity(fromActivity);
                                } else {
                                    mListener.onSignIn();
                                }
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
                            }
                        }
                    });
                }
            }
        });

        Button logoutButton = (Button)mView.findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        Button signupButton = (Button)mView.findViewById(R.id.registerButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onStartActivity(SignupFragment.class.getSimpleName());
                }
            }
        });

        return mView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onSignIn();
        public void onStartActivity(String activity);
    }

    public void signIn()
    {
        String userName = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        if (userName.length() == 0 || password.length() == 0) {
            mErrorField.setText(getResources().getString(R.string.enter_username));
            displayAlert(R.string.enter_username);
        } else {
            ParseUser.logInInBackground(userName, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        if (fromActivity != null) {
                            mListener.onStartActivity(fromActivity);
                        } else {
                            mListener.onSignIn();
                        }
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
                    }
                }
            });
        }
    }

    public void onLogout() {
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

    public void displayAlert(int messageId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(messageId)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
