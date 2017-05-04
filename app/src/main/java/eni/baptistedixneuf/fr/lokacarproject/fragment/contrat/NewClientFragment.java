package eni.baptistedixneuf.fr.lokacarproject.fragment.contrat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.voiture.VoitureContent;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.dao.ClientDao;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewClientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewClientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button enregistrer;
    private EditText nom;
    private EditText prenom;
    private EditText adresse;
    private EditText tel;
    private EditText mail;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewClientFragment newInstance(String param1, String param2) {
        NewClientFragment fragment = new NewClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewClientFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_client, container, false);
        enregistrer = (Button)view.findViewById(R.id.btEnregistrer);
        enregistrer.setOnClickListener(enregistrerListener);

        nom = (EditText) view.findViewById(R.id.editNom);
        prenom = (EditText) view.findViewById(R.id.editPrenom);
        adresse = (EditText) view.findViewById(R.id.editAdresse);
        tel = (EditText) view.findViewById(R.id.editTel);
        mail = (EditText) view.findViewById(R.id.editEmail);

        nom.setOnKeyListener(enterListener);
        prenom.setOnKeyListener(enterListener);
        adresse.setOnKeyListener(enterListener);
        tel.setOnKeyListener(enterListener);
        mail.setOnKeyListener(enterListener);

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

    private View.OnClickListener enregistrerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Client client = new Client();
            client.setNom(nom.getText().toString());
            client.setPrenom(prenom.getText().toString());
            client.setAdresse(adresse.getText().toString());
            client.setTel(tel.getText().toString());
            client.setEmail(mail.getText().toString());

            ClientDao dao = new ClientDao(getActivity());
            dao.add(client);
            int id = dao.getInsertId();
            client.setId(id);

            ChoisirVoitureFragment fragment = new ChoisirVoitureFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AjoutContratFragment.BUNDLE_CLIENT, client);
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, fragment)
                    .commit();
        }
    };

    private  View.OnKeyListener enterListener = new View.OnKeyListener()
    {
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN)
            {
                switch (keyCode)
                {
                    case android.view.KeyEvent.KEYCODE_DPAD_CENTER:
                    case android.view.KeyEvent.KEYCODE_ENTER:
                        NewClientFragment.this.enregistrer.performClick();
                        return true;
                    default:
                        break;
                }
            }
            return false;
        }
    };
}
