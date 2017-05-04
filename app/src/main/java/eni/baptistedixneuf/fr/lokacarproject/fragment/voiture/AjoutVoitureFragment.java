package eni.baptistedixneuf.fr.lokacarproject.fragment.voiture;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;
import eni.baptistedixneuf.fr.lokacarproject.dao.VoitureDao;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AjoutVoitureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AjoutVoitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjoutVoitureFragment extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Voiture  voiture;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AjoutVoitureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AjoutVoitureFragment newInstance(String param1, String param2) {
        AjoutVoitureFragment fragment = new AjoutVoitureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AjoutVoitureFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_ajout_voiture, container, false);
        voiture = new Voiture();
        final Button b = (Button)view.findViewById(R.id.saveVoiture);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText marque = (EditText)view.findViewById(R.id.editMarque);
                EditText modele = (EditText)view.findViewById(R.id.editModel);
                EditText immatriculation = (EditText)view.findViewById(R.id.editImat);
                EditText prix = (EditText)view.findViewById(R.id.editPrix);
                RadioButton cat1 = (RadioButton)view.findViewById(R.id.cat1);
                EditText couleur = (EditText)view.findViewById(R.id.editCouleur);

                if(verification(view))
                {
                    voiture.setMarque(marque.getText().toString());
                    voiture.setModele(modele.getText().toString());
                    voiture.setCouleur(couleur.getText().toString());
                    voiture.setImmatriculation(immatriculation.getText().toString());
                    voiture.setPrix(Double.parseDouble(prix.getText().toString()));

                    Categorie cat = new Categorie();
                    String categorie;
                    if(cat1.isChecked())
                    {
                        categorie = "categorie1";
                    }
                    else
                    {
                        categorie = "categorie2";
                    }
                    voiture.setCategorie(cat);
                    VoitureDao voitureDao = new VoitureDao(getActivity());
                    voitureDao.add(voiture);
                    Log.d("test", "Voiture enregistrer");
                }
                else
                {
                    Toast.makeText(getContext(), "Tous les champs doivent etre renseigné", Toast.LENGTH_LONG);
                }

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

    public void onClick(View v)
    {
        EditText marque = (EditText)v.findViewById(R.id.editMarque);
        EditText modele = (EditText)v.findViewById(R.id.editModel);
        EditText immatriculation = (EditText)v.findViewById(R.id.editImat);
        EditText prix = (EditText)v.findViewById(R.id.editPrix);
        RadioButton cat1 = (RadioButton)v.findViewById(R.id.cat1);
        EditText couleur = (EditText)v.findViewById(R.id.editCouleur);

        if(verification(v))
        {
            voiture.setMarque(marque.getText().toString());
            voiture.setModele(modele.getText().toString());
            voiture.setCouleur(couleur.getText().toString());
            voiture.setImmatriculation(immatriculation.getText().toString());
            voiture.setPrix(Double.parseDouble(prix.getText().toString()));

            Categorie cat = new Categorie();
            String categorie;
            if(cat1.isChecked())
            {
                categorie = "categorie1";
            }
            else
            {
                categorie = "categorie2";
            }
            voiture.setCategorie(cat);
            VoitureDao voitureDao = new VoitureDao(getActivity());
            voitureDao.add(voiture);
            Log.d("test", "Voiture enregistrer");
        }
        else
        {
            Toast.makeText(getContext(), "Tous les champs doivent etre renseigné", Toast.LENGTH_LONG);
        }
    }

    public boolean verification(View v)
    {
        boolean ok = true;

        EditText marque = (EditText)v.findViewById(R.id.editMarque);
        EditText modele = (EditText)v.findViewById(R.id.editModel);
        EditText immatriculation = (EditText)v.findViewById(R.id.editImat);
        EditText prix = (EditText)v.findViewById(R.id.editPrix);
        EditText couleur = (EditText)v.findViewById(R.id.editCouleur);
        if(marque ==null  || modele==null || immatriculation == null || prix == null || couleur == null)
        {
            ok=false;
        }
        else if(couleur.getText().toString().isEmpty() || marque.getText().toString().isEmpty() || modele.getText().toString().isEmpty() || immatriculation.getText().toString().isEmpty() || prix.getText().toString().isEmpty())
        {
            ok = false;
        }

        return ok;

    }

}
