package eni.baptistedixneuf.fr.lokacarproject.adaptater.contract;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class ContratAdaptater extends ArrayAdapter<Contrat> {

    public ContratAdaptater(Context context, List<Contrat> contrats) {
        super(context, 0, contrats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_liste_contrat,parent, false);
        }

        ContratViewHolder viewHolder = (ContratViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ContratViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.element_liste_contrat_id);
            viewHolder.dateDebut = (TextView) convertView.findViewById(R.id.element_liste_contrat_dateDebut);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.element_liste_contrat_nom);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.element_liste_contrat_prenom);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Contrat contrat = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.id.setText(""+contrat.getId());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        viewHolder.dateDebut.setText(dateFormatter.format(contrat.getDebut()));
        viewHolder.nom.setText(contrat.getClient().getNom());
        viewHolder.prenom.setText(contrat.getClient().getPrenom());
        return convertView;
    }

    private class ContratViewHolder {
        public TextView id;
        public TextView dateDebut;
        public TextView nom;
        public TextView prenom;
    }
}



