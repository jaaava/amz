package controllers;

import models.ItemLookupSample;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(null, null, null, null));
    }

    public static Result find(String find, Integer page, String cat) {
        List<String> titles = ItemLookupSample.find(find, page, cat, "Title");
        List<String> images = ItemLookupSample.find(find, page, cat, "URL");

        Map<String, List<String>> data = new HashMap<String, List<String>>();
        data.put("titles", titles);
        data.put("images", images);

        return ok(index.render(find, data, page, cat));
    }

}
