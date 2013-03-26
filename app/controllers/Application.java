package controllers;

import models.Item;
import models.ItemLookup;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.library;

import java.util.*;

public class Application extends Controller {
    private static Map<String, List<String>> perPageRequestData = new HashMap<String, List<String>>();
    private static List<List<String>> globalData = new ArrayList<List<String>>();

    public static Result index() {
        return ok(index.render(null, null, null, null));
    }

    public static Result find(String keyword, Integer pageNumber, String category) {
        System.out.print("------- REQUESTING PAGE ----------");
        List<String> titles = ItemLookup.find(keyword, pageNumber, category, "Title");
        List<String> images = ItemLookup.find(keyword, pageNumber, category, "Image");
        List<String> ASINs = ItemLookup.find(keyword, pageNumber, category, "ASIN");
        System.out.println("------- DONE ----------");

        /*
            List elements go to ArrayList, because View cannot render them correctly - they are nested.
            NOTE: List can have duplicates.
        */
        globalData.add(titles);
        globalData.add(images);
        globalData.add(ASINs);

        /*
            List elements go to HashMap, because View can render them correctly
            NOTE: Map DOES NOT allow duplicates - previous data will be overwritten; map per request!
        */
        perPageRequestData.put("titles", titles);
        perPageRequestData.put("images", images);
        perPageRequestData.put("asins", ASINs);

        return ok(index.render(keyword, perPageRequestData, pageNumber, category));
    }

    public static Result toLibrary(String asin){
        for (int i = 0; i < globalData.size(); i+=3) {
            List<String> asinList = globalData.get(i + 2);
            for (int j = asinList.size() - 1; j >= 0; j--) {
                if(asinList.get(j).equals(asin)){
                    String title = globalData.get(i + 0).get(j);
                    String image = globalData.get(i + 1).get(j);
                    //System.out.println("title:" + title + " image:" + image);
                    Item.add(new Item(title, image));
                    globalData.clear(); // kustuta olemasolevad andmed!
                    return ok(library.render());
                }
            }
            System.out.println();
        }

        return ok(library.render()); // error msg maybe?
    }
}
