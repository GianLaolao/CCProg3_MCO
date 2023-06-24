public class VendingMachine {
    
    private RegularVendo regular = null;
    private MoneyBox money = new MoneyBox();
    

    private Item searchItem(String itemName) {
        return regular.getItem(itemName);
    }


    public void createRegularVendo() {
        regular = new RegularVendo();
    }

    public void transaction(int payment) {
        //code here to take payment
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

    public boolean addItem(String name, int price, float calories) {
        
        Item item = new Item(name, price, calories);
        
        return regular.addItem(item);
    }

    public boolean restockItem(int quantity, String itemName) {
        Item item = searchItem(itemName);

        if (10 - item.getQuantity() >= quantity) {
            item.addQuantity(quantity);
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

}
