package controllers;

import models.ItemLookup;
import models.NodeHelper;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(null, null, null, null));
    }

    public static Result find(String keyword, Integer pageNumber, String category) {
        List<String> titles = ItemLookup.find(keyword, pageNumber, category, NodeHelper.ElementType.TITLES);
        List<String> images = ItemLookup.find(keyword, pageNumber, category, NodeHelper.ElementType.IMAGES);

        Map<String, List<String>> data = new HashMap<String, List<String>>();
        data.put("titles", titles);
        data.put("images", images);

        return ok(index.render(keyword, data, pageNumber, category));
    }
}
