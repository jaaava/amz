package models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: tarvo
 * Date: 3/26/13
 * Time: 9:25 PM
 */
public class DataHelper {

    private static HashMap<Integer, ArrayList<ArrayList<String>>> globalData = new HashMap<Integer, ArrayList<ArrayList<String>>>();

    public static void add(Integer page, ArrayList<ArrayList<String>> keyValues) {
        globalData.put(page, keyValues);
    }

    public static ArrayList<String> getElem(Integer page, Elem type) {
        return globalData.get(page).get(type.getType());
    }

    public static ArrayList<ArrayList<String>> getElemHolder(Integer page) {
        return globalData.get(page);
    }

    private static void printGlobalData(ArrayList<ArrayList<String>> arrayLists) {
        System.out.println("lehti g-s: " + globalData.size());

        for (int i = 0; i < arrayLists.get(Elem.Asin.getType()).size(); i++) {
            System.out.println("something: " + arrayLists.get(Elem.Asin.getType()).get(i));
        }
    }

    public enum Elem {
        Title(0), Image(1), Asin(2);
        private int type;

        Elem(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }
}
