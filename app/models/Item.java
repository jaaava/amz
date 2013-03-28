package models;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "postTable")
public class Item extends Model {
    public static Finder<Long, Item> find = new Finder(
            Long.class, Item.class);

    @Required
	@Id
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

    public static boolean add(Item item) {
        Iterator itemIter = Item.all().iterator();
        while(itemIter.hasNext()){
            Item itemInFocus = (Item)itemIter.next();
            if(itemInFocus.title.equals(item.title)){
                return false;
            }
        }

        item.save();
        return true;
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
