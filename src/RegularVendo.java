/*
 * A RegularVendo holds the eight (8) item slots available for purchase. 
 */

public class RegularVendo {
    //item array 
    private Item[] item;

/*
 * creates a RegularVendo object for item storage. 
 * 8 item slots are stored. 
 */

    public RegularVendo() {
        this.item = new Item[8];
    }

/*
 * adds item to the regular vending machine 
 * @param product 
 */

    public boolean addItem(Item product) {
        if (item != null || item.length > 0) {
            for(int i = 0; i < item.length; i++){
                if (item[i] == null){
                    item[i] = product;
                    return true;
                }
            }
        }
        return false;   
    }

/* 
 * removes item from the regular vending machine 
 * @param slot the slot opened for every item removed from the vending machine 
 */
    public boolean removeItem(int slot) {

        if (item[slot] != null){
            item[slot] = null;
            return true;
        }
       

        return false;
    }

/*
 * returns the item chosen by the user 
 * @return the item chosen by the user 
 */

    public Item[] getItems() {
        
        if (item != null)
            return item;
        
        return null;
    }

/*
 * returns the number of items restocked or replaced 
 * @param quantity the number of items added
 * @param item the specific item added 
 */

    public void restockItem(int quantity, Item item) {

        item.addQuantity(quantity);
    }

/*
 * sets the price for a chosen item 
 * @param price the price of the item 
 * @param slot the occupied slot of an item 
 */

    public void setItemPrice(int price, int slot) {

        getItem(slot).setPrice(price);
    }

/* 
 * returns the item selected 
 * @param slot the slot of the selected item 
 * @return the item select 
 */

    public Item getItem(int slot) {
        return item[slot];
    }

/*
 * returns the price of the selected item 
 * @param slot the slot of the selected item 
 * @return the price of the selected item 
 */

    public int getItemPrice(int slot) {
        return item[slot].getPrice();
    }
  
/* 
 * returns the item dispensed to the user 
 * @param slot the slot of the selected item 
 * @param quantity of the selected item 
 * @return the item dispensed to the user 
 */

    public Item dispenseItem(int slot, int quantity) {

        Item bought = getItem(slot);

        if (bought != null) {
            if (bought.getQuantity() != 0 && bought.getQuantity() > quantity)
                bought.sellItem(quantity);
                return bought;
        }
        
        return null;
    }

}
