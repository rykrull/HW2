package com.cs407_android.ormlab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link viewevent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link viewevent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewevent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private int year, month, day, i;

    private ListView list;
    private Button delete, calbutton;

    private ArrayAdapter adapter;
    private static ArrayList<String> elist;
    private static ArrayList<String> edlist;

    private OnFragmentInteractionListener mListener;

    public viewevent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @param param3 Parameter 3.
     * @return A new instance of fragment viewevent.
     */
    // TODO: Rename and change types and number of parameters
    public static viewevent newInstance(int param1, int param2, int param3) {
        edlist = new ArrayList<>();
        elist = new ArrayList<>();
        viewevent fragment = new viewevent();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            year = getArguments().getInt(ARG_PARAM1);
            month = getArguments().getInt(ARG_PARAM2);
            day = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewevent, container, false);
        list = (ListView) view.findViewById(R.id.listView);
        delete = (Button) view.findViewById(R.id.buttondelete);
        calbutton = (Button) view.findViewById(R.id.buttoncal);
        elist = ((MainActivity)getActivity()).getEventList();
        String date = month + " / " + day + " / " + year;
        for(String e : elist){
            if (e.contains(day+ "") && e.contains(month+ "") && e.contains(year+ "")){
                edlist.add(e);
            }
        }
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.itemlist, edlist);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

                view.setSelected(true);
                i = position;

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!elist.isEmpty()) {
                    String tmp = edlist.remove(i);
                    ((MainActivity) getActivity()).deleteEvent(elist.indexOf(tmp));
                    elist.remove(tmp);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        calbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, calendar.newInstance())
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
