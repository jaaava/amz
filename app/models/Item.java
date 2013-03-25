package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "postTable")
public class Item extends Model {
    public static Finder<Long, Item> find = new Finder(
            Long.class, Item.class);

    @Required
    public Long id;
    @Required
    public String title;
    @Required
    public String image;

    public Item(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public static List<Item> all() {
        return find.all();
    }

    public static void add(Item item) {
        item.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
