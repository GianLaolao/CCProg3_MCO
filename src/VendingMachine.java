import java.util.*;

public class VendingMachine {
    
    private RegularVendo regular = null;
    private ArrayList<Record> records = new ArrayList<>();
    private MoneyBox money = new MoneyBox();
    private float totalSales;
    
    public void createRegularVendo() {
        regular = new RegularVendo();
    }

    public void transaction(int payment, int slot) {
        
        //TODO
        //take payment, calculate change, give change
    }

    public Item dispenseItem(int itemNum, int quantity) {

        Item bought = regular.dispenseItem(itemNum, quantity);
        
        if (bought != null) {
            return bought;
        }

        return null;
    }
    

    public boolean addItem(String name, int price, float calories, int quantity) {
        
        Item item = new Item(name, price, calories, quantity);
        
        return regular.addItem(item);
    }

    public boolean removeItem(int slot) {
        return regular.removeItem(slot);
    }

    public boolean restockItem(int quantity, int slot) {
        
        return restockItem(quantity, slot);
    }

    public void setPrice(int price, int slot){

        regular.setPrice(price, slot);
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
    public int retrieveProfit() {
        return money.collectProfit();
    }

    public RegularVendo getRegular() {
        return regular;
    }
    public MoneyBox getMoney() {
        return money;
    }
    public ArrayList<Record> getRecords() {
        return records;
    }
    public float getTotalSales() {
        return totalSales;
    }

}
