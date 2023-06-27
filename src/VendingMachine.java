import java.util.*;

public class VendingMachine {
    
    private RegularVendo regular = null;
    private ArrayList<Record> records = new ArrayList<>();
    private MoneyBox money = new MoneyBox();
    private float totalSales;
    
    public void createRegularVendo() {
        regular = new RegularVendo();
    }

    public void transaction(int payment, Item item) {
        
        //TODO
        //take payment, calculate change, give change
    }

    public Item dispenseItem(int slot, int quantity) {

        Item bought = regular.dispenseItem(slot, quantity);
        Record record = getItemRecord(bought);
        
        if (bought != null && record != null) {

            record.setSold(quantity);
            return bought;
            
        }

        return null;
    }
    

    public boolean addItem(String name, int price, float calories, int quantity) {
        
        Item item = new Item(name, price, calories, quantity);
        Record record = new Record(item);
        records.add(record);
        
        return regular.addItem(item);
    }

    public boolean removeItem(int slot) {
        return regular.removeItem(slot);
    }

    public boolean restockItem(int quantity, int slot) {
        
        Item item = regular.getItem(slot);

        if (10 - item.getQuantity() >= quantity) {
            regular.restockItem(quantity, item);
            getItemRecord(item).setStartingInventory();
            getItemRecord(item).setSold(0);
            return true;
        }
            
        return false;
    }

    public boolean setItemPrice(int price, int slot){

        Item item = regular.getItem(slot);

        if(item != null) {
            regular.setItemPrice(price, slot);
            return true;
        }
           
        return false;
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
    public Record getItemRecord(Item item) {

        for (int i = 0; i < records.size(); i++){
            if (records.get(i).getItem().getName() == item.getName())
                return records.get(i);
        }
        return null;
    }
    public float getTotalSales() {
        return totalSales;
    }

}
