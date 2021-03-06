package com.polarion.starter.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.polarion.starter.activities.WebActivity;
import com.polarion.starter.utility.URLService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RaffleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaffleFragment extends Fragment {
    private View mView;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RaffleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RaffleFragment newInstance(String param1, String param2) {
        RaffleFragment fragment = new RaffleFragment();
        return fragment;
    }

    public RaffleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(com.polarion.starter.R.layout.fragment_raffle, container, false);
        Button button = (Button)mView.findViewById(com.polarion.starter.R.id.enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText testimonialEditText = (EditText) mView.findViewById(com.polarion.starter.R.id.testimonialText);
                CheckBox marketingUsage = (CheckBox) mView.findViewById(com.polarion.starter.R.id.marketingUsage);
                String testimonialText = testimonialEditText.getText().toString();
                if (testimonialText.length() == 0)
                    displayAlert(com.polarion.starter.R.string.provide_testimonial);
                else if (!marketingUsage.isChecked()) {
                    displayAlert(com.polarion.starter.R.string.check_marketing_usage);
                } else {
                    ParseObject testimonial = new ParseObject("Testimonial");
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    if (currentUser != null)
                        testimonial.put("user", currentUser);

                    testimonial.put("testimonial", testimonialText);
                    testimonial.saveEventually();
                    if (mListener != null) {
                        mListener.onEnterRaffle();
                    }
                }
            }
        });

        Button rulesButton = (Button)mView.findViewById(com.polarion.starter.R.id.rules);
        rulesButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               onOpenURL("rules.html","Raffle Rules");
                                           }
                                       }
        );

        Button samplesButton = (Button)mView.findViewById(com.polarion.starter.R.id.sampleButton);
        samplesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenURL("testimonial.html","Sample Testimonials");
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
        public void onEnterRaffle();
    }


    public void onOpenURL(String url, String title)
    {
        Intent webIntent = new Intent(getActivity().getApplicationContext(), WebActivity.class);
        webIntent.putExtra(WebActivity.ARG_URL_PARAM, URLService.buildUrl(url));
        webIntent.putExtra(WebActivity.ARG_TITLE_PARAM, title);
        startActivity(webIntent);
    }
}
