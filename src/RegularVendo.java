
import java.util.*;

public class RegularVendo {

    private ArrayList<Item> item = new ArrayList<>();
    private MoneyBox money;

    public void addItem(Item item) {
        this.item.add(item);
    }
    public void addMoney(int quanity, int type) {
        
        switch(type) {
            case 1:
                //code for adding one peso 
            case 2:
                //code for adding five peso 
            case 3: 
                //code for adding ten peso 
            case 4:
                //code for adding fifty peso 
            case 5: 
                //code for adding hundred peso 
        }
    }

    public ArrayList<Item> getItem() {
        return item;
    }
    public MoneyBox getMoney() {
        return money;
    }

    public boolean dispenseItem(int itemNum, int quantity) {

        //code for successful dispense of item

            // return true;

        
        return false;
    }

    public int retrieveMoney() {

        return money.collectMoney();
    }

}
