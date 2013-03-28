package controllers;

import models.DataHelper;
import models.Item;
import models.ItemLookup;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    // for grouping elements (Titles, Images, ASINs)
    private static ArrayList<ArrayList<String>> perRequestData = new ArrayList<ArrayList<String>>();

    public static Result index() {
        //return ok(library.render()); // error msg maybe?
        return ok(index.render(null, null, null, null));
    }

    public static Result find(String keyword, Integer pageNumber, String category) {
        System.out.print("------- REQUESTING PAGE ----------");
        ArrayList<String> titles = ItemLookup.find(keyword, pageNumber, category, "Title");
        ArrayList<String> images = ItemLookup.find(keyword, pageNumber, category, "Image");
        ArrayList<String> ASINs = ItemLookup.find(keyword, pageNumber, category, "ASIN");
        System.out.println("------- DONE ----------");

        // Group them into "per request holder"
        perRequestData.add(0, titles);
        perRequestData.add(1, images);
        perRequestData.add(2, ASINs);


        // Add holder to globally available HashMap<pageNr, ElemList>; It will be accessed from the View
        DataHelper.add(--pageNumber, perRequestData);
        //perRequestData.clear();
        // Let's render the page
        return ok(index.render(keyword, pageNumber, category, null));
    }

    public static Result toLibrary(String asin, Integer page) {
        for (int i = 0; i < perRequestData.size(); i += 3) {
            List<String> asinList = perRequestData.get(i + 2);
            for (int j = asinList.size() - 1; j >= 0; j--) {
                if (asinList.get(j).equals(asin)) {
                    String title = perRequestData.get(i + 0).get(j);
                    String image = perRequestData.get(i + 1).get(j);
                    //System.out.println("title:" + title + " image:" + image);
                    if (Item.add(new Item(title, image))) { // if item ISN'T already in item-library
                        //Item.add(new Item(title, image));
                        perRequestData.clear(); // kustuta olemasolevad andmed!
                        return ok(library.render());
                    } else {
                        return ok(index.render(null, null, null, "You already have this item in your library!"));
                    }
                }
            }
            System.out.println();
        }

        return ok(library.render()); // error msg maybe?
    }

    /*public static Result toLibrary(String asin, Integer page){
        ArrayList<ArrayList<String>> elemHolder = DataHelper.getElemHolder(page);
        System.out.println("otsisin page-lt: " + page);
        System.out.println(elemHolder.get(DataHelper.Elem.Asin.getType()).contains(asin));
        System.out.println("searching for: " + asin);
        System.out.println(Arrays.toString(elemHolder.get(DataHelper.Elem.Asin.getType()).toArray()));
        //String item = elemHolder.get(DataHelper.Elem.Title.getType()).
        //Item.add(new Item(item, image));
        return ok(library.render());
    }**/
}
