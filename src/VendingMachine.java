import java.util.*;

public class VendingMachine {
    
    private RegularVendo regular = null;
    private ArrayList<Record> records = new ArrayList<>();
    private MoneyBox vendoMoney = new MoneyBox();
    private MoneyBox userMoney = new MoneyBox();
    private int totalSales = 0;
    
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
                vendoMoney.getOnePeso().setQuantity(quantity);
            case 2:
                vendoMoney.getFivePeso().setQuantity(quantity);
            case 3: 
                vendoMoney.getTenPeso().setQuantity(quantity);
            case 4:
                vendoMoney.getFiftyPeso().setQuantity(quantity);
            case 5: 
                vendoMoney.getHundredPeso().setQuantity(quantity);
        }
    }

    public int produceChange(int payment, int price) {
        //TODO
        //calculate change and return change

        return 0;
    }

    public int retrieveProfit() {

        int profit;

        profit = vendoMoney.getOnePeso().getTotal() + vendoMoney.getFivePeso().getTotal() + vendoMoney.getTenPeso().getTotal() 
                + vendoMoney.getTwentyPeso().getTotal() + vendoMoney.getFiftyPeso().getTotal() + vendoMoney.getHundredPeso().getTotal();

        vendoMoney.getOnePeso().setQuantity(0);
        vendoMoney.getFivePeso().setQuantity(0);
        vendoMoney.getTenPeso().setQuantity(0);
        vendoMoney.getTwentyPeso().setQuantity(0);
        vendoMoney.getFiftyPeso().setQuantity(0);
        vendoMoney.getHundredPeso().setQuantity(0);


        return profit;
    }
   
    public RegularVendo getRegular() {
        return regular;
    }
    public MoneyBox getVendoMoney() {
        return vendoMoney;
    }
    public MoneyBox getUserMoney() {
        return userMoney;
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
    public int getTotalSales() {

        for(int i = 0; i < records.size(); i++) {
            totalSales += (records.get(i).getSold() * records.get(i).getItem().getPrice());
        }

        return totalSales;
    }

}
