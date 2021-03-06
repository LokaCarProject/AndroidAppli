package eni.baptistedixneuf.fr.lokacarproject.fragment.voiture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.adaptater.categorie.CategorieAdaptater;
import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.PhotosVoiture;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;
import eni.baptistedixneuf.fr.lokacarproject.dao.CategorieDao;
import eni.baptistedixneuf.fr.lokacarproject.dao.PhotosVoitureDao;
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


    private String mParam1;
    private String mParam2;

    private Voiture  voiture;

    private EditText marque;
    private EditText modele;
    private EditText couleur;
    private EditText immatriculation;
    private EditText prix;
    private Spinner spinner;
    private Categorie entry;

    private Button boutonEnregistrer;
    private Button boutonPrendreUnePhoto;

    private ImageView imageView;

    private OnFragmentInteractionListener mListener;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    static final int REQUEST_TAKE_PHOTO = 1;

    String mCurrentPhotoPath;

    List<PhotosVoiture> photos;

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
        photos = new ArrayList<>();

        CategorieDao catDao = new CategorieDao(getActivity());
        List<Categorie> categories = catDao.getAll();


        boutonEnregistrer = (Button)view.findViewById(R.id.saveVoiture);
        boutonEnregistrer.setOnClickListener(this);

        boutonPrendreUnePhoto = (Button)view.findViewById(R.id.buttonPhoto);
        boutonPrendreUnePhoto.setOnClickListener(this);

        imageView = (ImageView) view.findViewById(R.id.imageView);


        marque = (EditText)view.findViewById(R.id.editMarque);
        modele = (EditText)view.findViewById(R.id.editModel);
        immatriculation = (EditText)view.findViewById(R.id.editImat);
        prix = (EditText)view.findViewById(R.id.editPrix);
        couleur = (EditText)view.findViewById(R.id.editCouleur);
        spinner = (Spinner)view.findViewById(R.id.spiCate);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                entry = (Categorie)parent.getAdapter().getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CategorieAdaptater catAdapt = new CategorieAdaptater(getActivity(),catDao.getAll());
        catAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapt);



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
        Button boutonClique = (Button) v;

        switch (boutonClique.getId()){
            case R.id.buttonPhoto :
                cliqueSurPrendrePhoto(v);
                break;
            case R.id.saveVoiture :
                cliqueSurEnregistrer(v);
                break;
        }

    }

    private void cliqueSurEnregistrer(View v) {
        if(verification(v))
        {
            voiture.setMarque(marque.getText().toString());
            voiture.setModele(modele.getText().toString());
            voiture.setCouleur(couleur.getText().toString());
            voiture.setImmatriculation(immatriculation.getText().toString());
            voiture.setPrix(Double.parseDouble(prix.getText().toString()));

            voiture.setCategorie(entry);
            
            VoitureDao voitureDao = new VoitureDao(getActivity());
            voitureDao.add(voiture);
            voiture.setId(voitureDao.getInsertId());
            voiture.setPhotos(photos);
            for (PhotosVoiture itemPhoto: voiture.getPhotos()){
                itemPhoto.setVoiture(voiture);
                PhotosVoitureDao photosVoitureDao = new PhotosVoitureDao(getActivity());
                photosVoitureDao.add(itemPhoto);
            }
            Toast.makeText(getContext(), "Voiture enregistrée", Toast.LENGTH_LONG).show();
            AjoutVoitureFragment.this.getFragmentManager().popBackStack();

        }
        else
        {
            Toast.makeText(getContext(), "Tous les champs doivent etre renseigné", Toast.LENGTH_LONG).show();
        }
    }


    public boolean verification(View v)
    {
        boolean ok = true;

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

    private void cliqueSurPrendrePhoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.d("Test", ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Uri photo = Uri.fromFile(new File(mCurrentPhotoPath));
            imageView.setImageURI(photo);
        }

        PhotosVoiture newPhoto = new PhotosVoiture();
        newPhoto.setChemin(mCurrentPhotoPath);
        photos.add(newPhoto);
    }




}
