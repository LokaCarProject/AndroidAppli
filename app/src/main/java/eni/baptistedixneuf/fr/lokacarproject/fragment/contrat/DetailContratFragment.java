package eni.baptistedixneuf.fr.lokacarproject.fragment.contrat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailContratFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailContratFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailContratFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "contrat";

    private static Contrat contrat;

    //Partie client
    private TextView nom;
    private TextView prenom;
    private TextView tel;
    private TextView email;
    private TextView adresse;

    //Partie vehicule
    private TextView marque;
    private TextView modele;
    private TextView categorie;

    //partie Contrat
    private TextView dateDebut;
    private TextView dateFinPrev;
    private Switch rendu;
    private TextView dateRetour;




    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailContratFragment.
     */
    public static DetailContratFragment newInstance(String param1, String param2) {
        DetailContratFragment fragment = new DetailContratFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, contrat);
        fragment.setArguments(args);

        return fragment;
    }

    public DetailContratFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contrat = (Contrat)getArguments().getSerializable(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_contrat, container, false);

        nom = (TextView)view.findViewById(R.id.lblNom);
        prenom = (TextView)view.findViewById(R.id.lblPrenom);
        adresse = (TextView)view.findViewById(R.id.lblAdresse);
        tel = (TextView)view.findViewById(R.id.lblTel);
        email = (TextView)view.findViewById(R.id.lblMail);

        marque =(TextView)view.findViewById(R.id.lblMarque);
        modele = (TextView)view.findViewById(R.id.lblModele);
        categorie = (TextView)view.findViewById(R.id.lblCategorie);

        dateDebut = (TextView)view.findViewById(R.id.lblDateDebut);
        dateFinPrev = (TextView)view.findViewById(R.id.lblDateFin);

        fillInfo();

        return view;
    }


    private void fillInfo()
    {
        nom.setText(contrat.getClient().getNom());
        prenom.setText(contrat.getClient().getPrenom());
        adresse.setText(contrat.getClient().getAdresse());
        tel.setText(contrat.getClient().getTel());
        email.setText(contrat.getClient().getEmail());

        marque.setText(contrat.getVoiture().getMarque());
        modele.setText(contrat.getVoiture().getModele());
        categorie.setText(contrat.getVoiture().getCategorie().getNom());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

        dateDebut.setText(format.format(contrat.getDebut()));
        dateFinPrev.setText(format.format(contrat.getFinPrevue()));
    }
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
        public void onFragmentInteraction(Uri uri);
    }

}
