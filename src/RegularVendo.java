
import java.util.*;

public class RegularVendo {

    private ArrayList<Item> item = new ArrayList<>();
    private MoneyBox money;

    public void addItem(Item item) {
        this.item.add(item);
    }
    
    public void addMoney(int quantity, int type) {
        
        switch(type) {
            case 1:
                money.getOnePeso().setQuantity(quantity);
            case 2:
                money.getFivePeso().setQuantity(quantity);
            case 3: 
                money.getTenPeso().setQuantity(quantity);
            case 4:
                money.getFiftyPeso().setQuantity(quantity);
            case 5: 
                money.getHundredPeso().setQuantity(quantity);
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

    public int retrieveProfit() {

        return money.collectProft();
    }

    public void takePayment(int payment) {
        
        //takes payment
    }

}
