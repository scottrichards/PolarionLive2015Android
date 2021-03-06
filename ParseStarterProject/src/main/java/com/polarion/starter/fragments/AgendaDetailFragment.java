package com.polarion.starter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.polarion.starter.model.AgendaItem;


public class AgendaDetailFragment extends Fragment {
    private static final String ARG_ITEM = "item";


    private AgendaItem mItem;
    private View mView;
    private GridLayout mRatingLayout;
    private Button mRateButton;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaDetailFragment newInstance(String param1, String param2) {
        AgendaDetailFragment fragment = new AgendaDetailFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_ITEM, param1);
//        fragment.setArguments(args);
        return fragment;
    }

    public AgendaDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
 //           item = getArguments().getSerializable(ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(com.polarion.starter.R.layout.fragment_agenda_detail, container, false);
        TextView sessionTime = (TextView)mView.findViewById(com.polarion.starter.R.id.sessionTime);
        sessionTime.setText(mItem.getDisplayTime());
        TextView descriptionView = (TextView)mView.findViewById(com.polarion.starter.R.id.sessionDescription);
        descriptionView.setText(mItem.getDescription());
        TextView sessionName = (TextView)mView.findViewById(com.polarion.starter.R.id.sessionName);
        sessionName.setText(mItem.getSessionName());
        TextView speakerView = (TextView)mView.findViewById(com.polarion.starter.R.id.presenter);
        speakerView.setText(mItem.getSpeaker());
        final RatingBar contentBar = (RatingBar)mView.findViewById(com.polarion.starter.R.id.contentBarGrid);
        final RatingBar presenterBar = (RatingBar)mView.findViewById(com.polarion.starter.R.id.presenterBarGrid);
        mRateButton = (Button)mView.findViewById(com.polarion.starter.R.id.rateButton);
        mRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject sessionRating = new ParseObject("SessionRating");
                float contentRating = contentBar.getRating(), presenterRating = presenterBar.getRating();
                sessionRating.put("contentRating", contentRating);
                sessionRating.put("presenterRating", presenterRating);
                if (ParseUser.getCurrentUser() != null)
                    sessionRating.put("user", ParseUser.getCurrentUser());
                sessionRating.put("session",mItem);
                sessionRating.saveEventually();
            }
        });
        Boolean isRateable = mItem.isRateable();
        if (!isRateable) {
            mRateButton.setVisibility(View.INVISIBLE);
            mRatingLayout = (GridLayout)mView.findViewById(com.polarion.starter.R.id.ratingGridLayout);
            mRatingLayout.setVisibility(View.INVISIBLE);
        }
        return mView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
 //       mListener = null;
    }

    public void setAgendaItem(AgendaItem item) {
        mItem = item;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
