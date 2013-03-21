package controllers;

import models.ItemLookupSample;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(null, null, null, null));
    }

    public static Result find(String find, Integer page, String cat) {
        List<String> found = ItemLookupSample.find(find, page, cat);
        return ok(index.render(find, found, page, cat));
    }

}
