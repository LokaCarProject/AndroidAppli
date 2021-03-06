package eni.baptistedixneuf.fr.lokacarproject.fragment.contrat;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.categorie.CategorieAdaptater;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.voiture.VoitureAdaptater;
import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;
import eni.baptistedixneuf.fr.lokacarproject.dao.CategorieDao;
import eni.baptistedixneuf.fr.lokacarproject.dao.VoitureDao;
import eni.baptistedixneuf.fr.lokacarproject.fragment.contrat.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ChoisirVoitureFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ALL_CAT = "Toutes";

    private ListView listeVoitures;
    private Spinner spinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
    private Client client;

    // TODO: Rename and change types of parameters
    public static ChoisirVoitureFragment newInstance(String param1, String param2) {
        ChoisirVoitureFragment fragment = new ChoisirVoitureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChoisirVoitureFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            client = (Client)getArguments().getSerializable(AjoutContratFragment.BUNDLE_CLIENT);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voiture_list, container, false);

        listeVoitures = (ListView) view.findViewById(R.id.list_voiture);
        VoitureDao dao = new VoitureDao(getActivity());
        VoitureAdaptater adapter = new VoitureAdaptater(getActivity(), dao.getAll());
        listeVoitures.setAdapter(adapter);
        listeVoitures.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                InfoContratFragment fragment = new InfoContratFragment();
                Voiture entry = (Voiture) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(AjoutContratFragment.BUNDLE_CLIENT, client);
                bundle.putSerializable(AjoutContratFragment.BUNDLE_VOITURE, entry);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });

        spinner = (Spinner) view.findViewById(R.id.spinnerCategorie);
        CategorieDao cDao = new CategorieDao(getActivity());
        List<Categorie> categories = cDao.getAll();
        Categorie cat = new Categorie();
        cat.setId(0);
        cat.setNom(ALL_CAT);
        categories.add(0, cat);
        CategorieAdaptater cAdaptater = new CategorieAdaptater(getActivity(), categories);
        cAdaptater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(cAdaptater);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Categorie entry = (Categorie) parent.getAdapter().getItem(position);
                VoitureDao dao = new VoitureDao(getActivity());
                List<Voiture> voitures = new ArrayList<>();
                if(entry.getNom().equals(ALL_CAT)){
                   voitures = dao.getAll();
                } else {
                    voitures = dao.getAllByCategorie(entry.getId());
                }

                VoitureAdaptater adapter = new VoitureAdaptater(getActivity(), voitures);
                ChoisirVoitureFragment.this.listeVoitures.setAdapter(adapter);
                ChoisirVoitureFragment.this.listeVoitures.deferNotifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
