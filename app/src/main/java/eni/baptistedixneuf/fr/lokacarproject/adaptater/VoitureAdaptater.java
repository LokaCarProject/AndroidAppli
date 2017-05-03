package eni.baptistedixneuf.fr.lokacarproject.adaptater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class VoitureAdaptater extends ArrayAdapter<Voiture> {

    public VoitureAdaptater(Context context, List<Voiture> voitures) {
        super(context, 0, voitures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_liste_voiture,parent, false);
        }

        VoitureViewHolder viewHolder = (VoitureViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new VoitureViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.element_liste_voiture_nom);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Voiture voiture = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(voiture.getImmatriculation());
        return convertView;
    }

    private class VoitureViewHolder {
        public TextView nom;

    }
}



