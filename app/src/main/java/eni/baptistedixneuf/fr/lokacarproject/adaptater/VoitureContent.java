package eni.baptistedixneuf.fr.lokacarproject.adaptater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eni.baptistedixneuf.fr.lokacarproject.bo.Voiture;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class VoitureContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Voiture> ITEMS = new ArrayList<Voiture>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, Voiture> ITEM_MAP = new HashMap<Integer, Voiture>();

    public static void addItem(Voiture item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
