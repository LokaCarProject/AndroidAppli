package eni.baptistedixneuf.fr.lokacarproject.fragment.voiture;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailVoitureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailVoitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailVoitureFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "Voiture";

    // TODO: Rename and change types of parameters
    private static Voiture voiture;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailVoitureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailVoitureFragment newInstance(String param1, String param2) {
        DetailVoitureFragment fragment = new DetailVoitureFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, voiture);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailVoitureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            voiture = (Voiture)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_voiture, container, false);
        Log.d("Test", voiture.toString());


        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
