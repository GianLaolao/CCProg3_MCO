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
        
         switch(type){
            case 1:
                vendoMoney.setOnePeso(vendoMoney.getOnePeso().getQuantity() + quantity);
            case 2:
                vendoMoney.setFivePeso(vendoMoney.getFivePeso().getQuantity() + quantity);
            case 3: 
                vendoMoney.setTenPeso(vendoMoney.getTenPeso().getQuantity() + quantity);
            case 4:
                vendoMoney.setTwentyPeso(vendoMoney.getTwentyPeso().getQuantity() + quantity);
            case 5:
                vendoMoney.setFiftyPeso(vendoMoney.getFiftyPeso().getQuantity() + quantity);
            case 6:
                vendoMoney.setHundredPeso(vendoMoney.getHundredPeso().getQuantity() + quantity);
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

        MoneyBox changeBox = new MoneyBox(); //stores change
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

                int amount = change % 100;

                if (amount > vendoMoney.getHundredPeso().getQuantity()) {
                    change -= vendoMoney.getHundredPeso().getQuantity() * 100;
                    vendoMoney.getHundredPeso().setQuantity(0);
                }
                else {
                    change -= amount * 100;
                    vendoMoney.getHundredPeso().setQuantity(vendoMoney.getHundredPeso().getQuantity() - amount);
                }
                changeBox.getHundredPeso().setQuantity(amount);

            }
            else if (vendoMoney.getFiftyPeso().getQuantity() != 0){

                int amount = change % 50;

                if (amount > vendoMoney.getFiftyPeso().getQuantity()) {
                    change -= vendoMoney.getFiftyPeso().getQuantity() * 50;
                    vendoMoney.getFiftyPeso().setQuantity(0);
                }
                else {
                    change -= amount * 50;
                    vendoMoney.getFiftyPeso().setQuantity(vendoMoney.getFiftyPeso().getQuantity() - amount);
                }
                changeBox.getFiftyPeso().setQuantity(amount);
            }
            else if (vendoMoney.getTwentyPeso().getQuantity() != 0){

                int amount = change % 20;

                if (amount > vendoMoney.getTwentyPeso().getQuantity()) {
                    change -= vendoMoney.getTwentyPeso().getQuantity() * 20;
                    vendoMoney.getTwentyPeso().setQuantity(0);
                }
                else {
                    change -= amount * 20;
                    vendoMoney.getTwentyPeso().setQuantity(vendoMoney.getTwentyPeso().getQuantity() - amount);
                }
                changeBox.getTwentyPeso().setQuantity(amount);
            }
            else if (vendoMoney.getTenPeso().getQuantity() != 0){

                int amount = change % 10;

                if (amount > vendoMoney.getTenPeso().getQuantity()) {
                    change -= vendoMoney.getTenPeso().getQuantity() * 10;
                    vendoMoney.getTenPeso().setQuantity(0);
                }
                else {
                    change -= amount * 10;
                    vendoMoney.getTenPeso().setQuantity(vendoMoney.getTenPeso().getQuantity() - amount);
                }
                changeBox.getTenPeso().setQuantity(amount);
            }
            else if (vendoMoney.getFivePeso().getQuantity() != 0){

                int amount = change % 5;

                if (amount > vendoMoney.getFivePeso().getQuantity()) {
                    change -= vendoMoney.getFivePeso().getQuantity() * 5;
                    vendoMoney.getFivePeso().setQuantity(0);
                }
                else {
                    change -= amount * 5;
                    vendoMoney.getFivePeso().setQuantity(vendoMoney.getFivePeso().getQuantity() - amount);
                }
                changeBox.getFivePeso().setQuantity(amount);
            }
            else if (vendoMoney.getOnePeso().getQuantity() != 0){

                int amount = change % 1;

                if (amount > vendoMoney.getOnePeso().getQuantity()) {
                    change -= vendoMoney.getOnePeso().getQuantity();
                    vendoMoney.getOnePeso().setQuantity(0);
                }
                else {
                    change -= amount;
                    vendoMoney.getOnePeso().setQuantity(vendoMoney.getOnePeso().getQuantity() - amount);
                }
                changeBox.getOnePeso().setQuantity(amount);
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
