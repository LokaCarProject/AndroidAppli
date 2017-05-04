package eni.baptistedixneuf.fr.lokacarproject.adaptater.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eni.baptistedixneuf.fr.lokacarproject.R;
import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class ClientAdaptater extends ArrayAdapter<Client> {

    public ClientAdaptater(Context context, List<Client> clients) {
        super(context, 0, clients);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.element_liste_client,parent, false);
        }

        ContratViewHolder viewHolder = (ContratViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ContratViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.element_liste_client_id);
            viewHolder.mail = (TextView) convertView.findViewById(R.id.element_liste_client_mail);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.element_liste_client_nom);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.element_liste_client_prenom);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Client client = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.id.setText(""+client.getId());
        viewHolder.mail.setText(client.getEmail());
        viewHolder.nom.setText(client.getNom());
        viewHolder.prenom.setText(client.getPrenom());
        return convertView;
    }

    private class ContratViewHolder {
        public TextView id;
        public TextView nom;
        public TextView prenom;
        public TextView mail;
    }
}



