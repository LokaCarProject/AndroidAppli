package eni.baptistedixneuf.fr.lokacarproject.adaptater.categorie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Categorie;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class CategorieAdaptater extends ArrayAdapter<Categorie> {

    public CategorieAdaptater(Context context, List<Categorie> categories) {
        super(context, 0, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_liste_categorie,parent, false);
        }

        CategorieViewHolder viewHolder = (CategorieViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CategorieViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.element_liste_cat_nom);
            viewHolder.id = (TextView) convertView.findViewById(R.id.element_liste_cat_id);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Categorie categorie = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(categorie.getNom());
        viewHolder.id.setText("" + categorie.getId());
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_liste_categorie,parent, false);
        }

        CategorieViewHolder viewHolder = (CategorieViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CategorieViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.element_liste_cat_nom);
            viewHolder.id = (TextView) convertView.findViewById(R.id.element_liste_cat_id);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Categorie categorie = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(categorie.getNom());
        viewHolder.id.setText("" + categorie.getId());
        return convertView;
    }

    private class CategorieViewHolder {
        public TextView nom;
        public TextView id;
    }
}



