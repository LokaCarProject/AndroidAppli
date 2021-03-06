package eni.baptistedixneuf.fr.lokacarproject.fragment.voiture;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.voiture.VoitureAdaptater;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.voiture.VoitureContent;
import eni.baptistedixneuf.fr.lokacarproject.dao.VoitureDao;
import eni.baptistedixneuf.fr.lokacarproject.fragment.contrat.DetailContratFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoitureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoitureFragment extends Fragment {

    private ListView listeVoiture;
    private Button buttonAjoutVoiture;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VoitureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoitureFragment newInstance(String param1, String param2) {
        VoitureFragment fragment = new VoitureFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public VoitureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_voiture, container, false);

        VoitureDao voitureDao = new VoitureDao(getActivity());
        VoitureContent.ITEMS = voitureDao.getAll();

        listeVoiture = (ListView) view.findViewById(R.id.fragement_voiture_listView);
        VoitureAdaptater adapter = new VoitureAdaptater(getActivity(), VoitureContent.ITEMS);
        listeVoiture.setAdapter(adapter);
        listeVoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DetailVoitureFragment fragment = new DetailVoitureFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetailVoitureFragment.ARG_PARAM1, VoitureContent.ITEMS.get(position));
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        buttonAjoutVoiture = (Button) view.findViewById(R.id.fragement_voiture_button);
        buttonAjoutVoiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, new AjoutVoitureFragment(), "NewFragmentTag");
                ft.commit();
            }
        });
        
        // Inflate the layout for this fragment
        return view;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
