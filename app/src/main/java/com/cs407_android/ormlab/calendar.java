package com.cs407_android.ormlab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link calendar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calendar extends Fragment {

    private Button addButton, viewButton;
    private CalendarView calendarView;
    private TextView dateDisplay;
    private int day;
    private int month;
    private int year;

    private OnFragmentInteractionListener mListener;

    public calendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static calendar newInstance() {
        calendar fragment = new calendar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = (CalendarView) view.findViewById(R.id.calendarView2);
        addButton = (Button) view.findViewById(R.id.buttonadd);
        viewButton = (Button) view.findViewById(R.id.buttonview);
        dateDisplay = (TextView) view.findViewById(R.id.dateText);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int i, int i1, int i2) {
                dateDisplay.setText("Date: " + i1 + " / " + i2 + " / " + i);
                day = i2;
                month = i1;
                year = i;
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, event.newInstance(year, month, day))
                        .addToBackStack(null)
                        .commit();
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, viewevent.newInstance(year, month, day))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
