
import java.util.*;

public class RegularVendo {

    private ArrayList<Item> item = new ArrayList<>();
    
    private Item searchItem(String itemName) {

        for (Item a : item) {
            if (a.getName().contains(itemName))
                return a;
        }

        return null;
    }
    
    public boolean addItem(Item product) {
        if (this.item.size() < 8) {
            this.item.add(product);
            return true;
        }
         return false;   
    }
    

    public ArrayList<Item> getItems() {
        return item;
    }

    public Item getItem(String itemName) {
        return searchItem(itemName);
    }
  
    public Item dispenseItem(int itemNum, int quantity) {

        //code for successful dispense of item

            // return true;

        
        return null;
    }

}
