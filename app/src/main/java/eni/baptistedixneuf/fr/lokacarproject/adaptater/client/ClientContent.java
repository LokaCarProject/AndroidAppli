package eni.baptistedixneuf.fr.lokacarproject.adaptater.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eni.baptistedixneuf.fr.lokacarproject.bo.Client;
import eni.baptistedixneuf.fr.lokacarproject.bo.Contrat;

/**
 * Created by bdixneuf2015 on 03/05/2017.
 */
public class ClientContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Client> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, Client> ITEM_MAP = new HashMap<>();

    public static void addItem(Client item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
