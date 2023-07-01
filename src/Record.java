/* 
 * A Record represents the vending machine's inventory and record for items sold. It contains 
 * the items available, items sold, and starting inventory.  
 * The number of items sold are initialized as zero. 
 */

public class Record {
    private Item item;
    private int sold = 0;
    private int startingInventory;
    private int soldAmount = 0;

/*
 * creates a Record object given a specific item 
 * @param item refers to the item being recorded 
 */

    public Record(Item item) {
        this.item = item;
        setStartingInventory();
    }

/* 
 * sets the number of items sold by incrementing the sold items
 * @param sold the number of items sold 
 */
    public void setSold(int sold) {
        this.sold = sold;
    }

/* 
 * sets the starting inventory of the vending machine 
 */

    public void setStartingInventory() {
        this.startingInventory = item.getQuantity();
    }

/*
 * sets the sold amount
 * @param quantity the quantity of item sold
 */

    public void setSoldAmount(int quantity) {
        this.soldAmount += item.getPrice() * quantity;
    }
        
/*
 * resets the sold amount to 0
 */
    public void resetSoldAmount() {
        this.soldAmount = 0;
    }


/* 
 * returns the item recorded 
 */

    public Item getItem() {
        return item;
    }

/* 
 * returns the number of items sold  
 */

    public int getSold() {
        return sold;
    }

/*
 * returns the starting inventory of the vending machine  
 */
    public int getStartingInventory() {
        return startingInventory;
    }

/*
 * returns the item sold amount
 */
    public int getSoldAmount() {
        return soldAmount;
    }
}
