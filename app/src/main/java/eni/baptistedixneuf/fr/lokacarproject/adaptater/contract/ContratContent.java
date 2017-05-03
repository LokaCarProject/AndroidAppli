package eni.baptistedixneuf.fr.lokacarproject.adaptater.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;
import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class ContratContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Contrat> ITEMS = new ArrayList<Contrat>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, Contrat> ITEM_MAP = new HashMap<Integer, Contrat>();

    public static void addItem(Contrat item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
