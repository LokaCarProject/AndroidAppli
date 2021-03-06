package eni.baptistedixneuf.fr.lokacarproject.fragment.contrat;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.contract.ContratAdaptater;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.contract.ContratContent;
import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;
import eni.baptistedixneuf.fr.lokacarproject.dao.ContratDao;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContractFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContractFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContractFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listeContrats;
    private Button buttonAjoutContrat;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContractFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContractFragment newInstance(String param1, String param2) {
        ContractFragment fragment = new ContractFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ContractFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_contract, container, false);

        listeContrats = (ListView) view.findViewById(R.id.fragement_contrats_listView);
        ContratDao dao = new ContratDao(getActivity());
        ContratContent.ITEMS = dao.getAll();
        ContratAdaptater adapter = new ContratAdaptater(getActivity(), ContratContent.ITEMS);
        listeContrats.setAdapter(adapter);
        listeContrats.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {

                DetailContratFragment fragment = new DetailContratFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetailContratFragment.ARG_PARAM1, ContratContent.ITEMS.get(position));
                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        buttonAjoutContrat = (Button) view.findViewById(R.id.fragement_contrats_button);
        buttonAjoutContrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.container, new AjoutContratFragment(), "NewFragmentTag");
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
