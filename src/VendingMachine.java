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

    public void takePayment(int type) {

        switch(type){
            case 1:
                userMoney.setOnePeso(userMoney.getOnePeso().getQuantity() + 1);
                break;
            case 2:
                userMoney.setFivePeso(userMoney.getFivePeso().getQuantity() + 1);
                break;
            case 3:
                userMoney.setTenPeso(userMoney.getTenPeso().getQuantity() + 1);
                break;
            case 4:
                userMoney.setTwentyPeso(userMoney.getTwentyPeso().getQuantity() + 1);
                break;
            case 5:
                userMoney.setFiftyPeso(userMoney.getFiftyPeso().getQuantity() + 1);
                break;
            case 6:
                userMoney.setHundredPeso(userMoney.getHundredPeso().getQuantity() + 1);
                break;
        }
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

        if (regular.getItem(slot) != null) {
            regular.removeItem(slot);
            return true;
        }   
        
        return false;
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
        
         switch(type){
            case 1:
                vendoMoney.setOnePeso(vendoMoney.getOnePeso().getQuantity() + quantity);
                break;
            case 2:
                vendoMoney.setFivePeso(vendoMoney.getFivePeso().getQuantity() + quantity);
                break;
            case 3: 
                vendoMoney.setTenPeso(vendoMoney.getTenPeso().getQuantity() + quantity);
                break;
            case 4:
                vendoMoney.setTwentyPeso(vendoMoney.getTwentyPeso().getQuantity() + quantity);
                break;
            case 5:
                vendoMoney.setFiftyPeso(vendoMoney.getFiftyPeso().getQuantity() + quantity);
                break;
            case 6:
                vendoMoney.setHundredPeso(vendoMoney.getHundredPeso().getQuantity() + quantity);
                break;
        }
    }

    public boolean checkUserMoney(int slot, int quantity) {

        Item item = regular.getItem(slot);
        int price = item.getPrice() * quantity;

        if (price > userMoney.getTotal()){
            return false;
        }

        return true;
    }

    public MoneyBox produceChange(int slot, int quantity) {

        MoneyBox changeBox = new MoneyBox(); 
        Item item = regular.getItem(slot);
        int change = userMoney.getTotal() - (item.getPrice() * quantity);

        if(change > vendoMoney.getTotal()) {
            return null;
        }

        addMoney(userMoney.getOnePeso().getQuantity(), 1);
        addMoney(userMoney.getFivePeso().getQuantity(), 2);
        addMoney(userMoney.getTenPeso().getQuantity(), 3);
        addMoney(userMoney.getTwentyPeso().getQuantity(), 4);
        addMoney(userMoney.getFiftyPeso().getQuantity(), 5);
        addMoney(userMoney.getHundredPeso().getQuantity(), 6);

        userMoney.setOnePeso(0);
        userMoney.setFivePeso(0);
        userMoney.setTenPeso(0);
        userMoney.setTwentyPeso(0);
        userMoney.setFiftyPeso(0);
        userMoney.setHundredPeso(0);

        do {
            if (vendoMoney.getHundredPeso().getQuantity() != 0){

                int amount = change / 100;

                if ((change / 100 != 0) && amount > vendoMoney.getHundredPeso().getQuantity()) {
                    change -= vendoMoney.getHundredPeso().getQuantity() * 100;
                    vendoMoney.setHundredPeso(0);
                    changeBox.setHundredPeso(amount);
                }
                else if (change / 100 != 0) {
                    change -= amount * 100;
                    vendoMoney.setHundredPeso(vendoMoney.getHundredPeso().getQuantity() - amount);
                    changeBox.setHundredPeso(amount);
                }
                
            }
            if (vendoMoney.getFiftyPeso().getQuantity() != 0){

                int amount = change / 50;

                if ((change / 50 != 0) && amount > vendoMoney.getFiftyPeso().getQuantity()) {
                    change -= vendoMoney.getFiftyPeso().getQuantity() * 50;
                    vendoMoney.setFiftyPeso(0);
                    changeBox.setFiftyPeso(amount);
                }
                else if (change /50 != 0) {
                    change -= amount * 50;
                    vendoMoney.setFiftyPeso(vendoMoney.getFiftyPeso().getQuantity() - amount);
                    changeBox.setFiftyPeso(amount);
                }
                
            }
            if (vendoMoney.getTwentyPeso().getQuantity() != 0){

                int amount = change / 20;

                if ((change / 20 != 0) && amount > vendoMoney.getTwentyPeso().getQuantity()) {
                    change -= vendoMoney.getTwentyPeso().getQuantity() * 20;
                    vendoMoney.setTwentyPeso(0);
                    changeBox.setTwentyPeso(amount);
                }
                else if (change / 20 != 0){
                    change -= amount * 20;
                    vendoMoney.setTwentyPeso(vendoMoney.getTwentyPeso().getQuantity() - amount);
                    changeBox.setTwentyPeso(amount);
                }
            }
            if (vendoMoney.getTenPeso().getQuantity() != 0){

                int amount = change / 10;

                if ((change / 10 != 0) && amount > vendoMoney.getTenPeso().getQuantity()) {
                    change -= vendoMoney.getTenPeso().getQuantity() * 10;
                    vendoMoney.setTenPeso(0);
                    changeBox.setTenPeso(amount);
                }
                else if (change / 10 != 0){
                    change -= amount * 10;
                    vendoMoney.setTenPeso(vendoMoney.getTenPeso().getQuantity() - amount);
                    changeBox.setTenPeso(amount);
                }
               
            }
            if (vendoMoney.getFivePeso().getQuantity() != 0){

                int amount = change / 5;

                if ((change / 5 != 0) && amount > vendoMoney.getFivePeso().getQuantity()) {
                    change -= vendoMoney.getFivePeso().getQuantity() * 5;
                    vendoMoney.setFivePeso(0);
                    changeBox.setFivePeso(amount);
                }
                else if (change / 5 != 0){
                    change -= amount * 5;
                    vendoMoney.setFivePeso(vendoMoney.getFivePeso().getQuantity() - amount);
                    changeBox.setFivePeso(amount);
                }
                
            }
            if (vendoMoney.getOnePeso().getQuantity() != 0){

                int amount = change / 1;

                if ((change / 1 != 0) && amount > vendoMoney.getOnePeso().getQuantity()) {
                    change -= vendoMoney.getOnePeso().getQuantity();
                    vendoMoney.setOnePeso(0);
                    changeBox.setOnePeso(amount);
                }
                else if (change / 1 != 0){
                    change -= amount;
                    vendoMoney.setOnePeso(vendoMoney.getOnePeso().getQuantity() - amount);
                    changeBox.setOnePeso(amount);
                }
                
            }
        } while (change != 0);

        return changeBox;
    }

    public int retrieveProfit() {

        int profit;

        profit = vendoMoney.getTotal();

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
