package eni.baptistedixneuf.fr.lokacarproject.fragment.contrat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;
import eni.baptistedixneuf.fr.lokacarproject.dao.ContratDao;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoContratFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoContratFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoContratFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Client client;
    private Voiture voiture;
    private Button enregistrer;
    private EditText dbt;
    private EditText fin;

    private DatePickerDialog dbtDatePickerDialog;
    private DatePickerDialog finDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoContratFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoContratFragment newInstance(String param1, String param2) {
        InfoContratFragment fragment = new InfoContratFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InfoContratFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            client = (Client)getArguments().getSerializable(AjoutContratFragment.BUNDLE_CLIENT);
            voiture = (Voiture)getArguments().getSerializable(AjoutContratFragment.BUNDLE_VOITURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_contrat, container, false);
        // Inflate the layout for this fragment
        dbt = (EditText) view.findViewById(R.id.editDbt);
        dbt.setOnClickListener(this);
        dbt.setInputType(InputType.TYPE_NULL);

        fin = (EditText) view.findViewById(R.id.editFin);
        fin.setOnClickListener(this);
        fin.setInputType(InputType.TYPE_NULL);

        enregistrer = (Button) view.findViewById(R.id.bt_enregistrer);
        enregistrer.setOnClickListener(enregistrerListener);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

        setDateTimeField();

        return view;
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        dbtDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dbt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        finDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fin.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
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
            try {
                Date dateDbt = dateFormatter.parse(dbt.getText().toString());
                Date dateFin = dateFormatter.parse(fin.getText().toString());
                Contrat contrat = new Contrat();
                contrat.setDebut(dateDbt);
                contrat.setFinPrevue(dateFin);
                contrat.setRendu(false);
                contrat.setClient(client);
                contrat.setVoiture(voiture);
                ContratDao dao = new ContratDao(getActivity());
                dao.add(contrat);

                ContractFragment fragment = new ContractFragment();
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();
            } catch (ParseException e){
                e.printStackTrace();
            }


        }
    };

    @Override
    public void onClick(View view) {
        if(view == dbt) {
        dbtDatePickerDialog.show();
        } else if(view == fin) {
            finDatePickerDialog.show();
        }
    }
}
