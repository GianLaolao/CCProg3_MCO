
import java.util.*;

public class RegularVendo {

    private ArrayList<Item> item = new ArrayList<>();
        
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

    public boolean restockItem(int quantity, int slot) {

        Item item = getItem(slot);

        if (10 - item.getQuantity() >= quantity) {
            item.addQuantity(quantity);
            return true;
        }
            
        return false;
    }

    public Item getItem(int slot) {
        return item.get(slot);
    }
  
    public Item dispenseItem(int slot, int quantity) {

        //code for successful dispense of item

            // return true;

        
        return null;
    }

}
