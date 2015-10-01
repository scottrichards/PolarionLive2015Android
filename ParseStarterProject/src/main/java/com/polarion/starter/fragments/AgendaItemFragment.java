package com.polarion.starter.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.polarion.starter.adapters.AgendaAdapter;
import com.polarion.starter.model.AgendaItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class AgendaItemFragment extends ListFragment implements AbsListView.OnItemClickListener {



    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private AgendaAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static AgendaItemFragment newInstance(String param1, String param2) {
        AgendaItemFragment fragment = new AgendaItemFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgendaItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseObject.registerSubclass(AgendaItem.class);
        mAdapter = new AgendaAdapter(getActivity(), new ArrayList<AgendaItem>());
        updateData();
    }

    public void updateData(){
        ParseQuery<AgendaItem> query = ParseQuery.getQuery(AgendaItem.class);
//        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);

        query.findInBackground(new FindCallback<AgendaItem>() {
            @Override
            public void done(List<AgendaItem> agendaItems, ParseException error) {
                if (agendaItems != null) {
                    mAdapter.clear();
                    String currentDateString, lastDateString = null;
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd");

                    for (int i = 0; i < agendaItems.size(); i++) {
                        AgendaItem agendaItem = agendaItems.get(i);
                        currentDateString = sdf.format(agendaItem.getStartDate());
                        if (currentDateString.equals(lastDateString)) {
                            mAdapter.addItem(agendaItem);
                        } else {
                            mAdapter.addSeparatorItem(agendaItem,currentDateString);
                        }
                        lastDateString = currentDateString;
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.polarion.starter.R.layout.fragment_agenda, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mListView.setAdapter(mAdapter);
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

    // Why does this not get called but commenting it out causes an ERROR
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
                mListener.onSelectAgendaItem(mAdapter.getItem(position));
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onSelectAgendaItem(mAdapter.getItem(position));

//            FragmentManager fragmentManager = getFragmentManager();
//            AgendaDetailFragment fragment;
//            fragment = new AgendaDetailFragment();
//            fragment.setAgendaItem(mAdapter.getItem(position));
//            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(AgendaDetailFragment.class.getSimpleName()).commit();
         }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onSelectAgendaItem(AgendaItem id);
    }

}
