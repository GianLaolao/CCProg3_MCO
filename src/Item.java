/*
 * An Item represents a vending machine item available for purchase. It contains 
 * the name of the item, its price, amount of calories, and quantity. 
 */

public class Item {
    //initialize variables 
    private String name;
    private int price;
    private float calories;
    private int quantity = 0;

    /*
     * creates an Item object given the name of the item, its price, amount of calories, and quantity. 
     * @param name the name of the item 
     * @param price the price of the item 
     * @param calories the amount of calories of the item
     * @param quantity the quantity of the item
     */

    public Item (String name, int price, float calories, int quantity) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
    }

    /*
     * sets the exact price for each item
     * @param price the price of the item 
     */

    public void setPrice(int price) {
        this.price = price;
    } 
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /*
     * sells item from the vending machine by decrementing the quantity of items
     * @param quantity the quantity of the chosen item 
     */

    public void sellItem (int quantity) {
        this.quantity -= quantity;
    }

    /* 
     * returns the name of the chosen item 
     * @return the nume of the chosen item 
     */

    public String getName() {
        return name;
    }

    /*
     * returns the price of the chosen item 
     * @return the price of the chosen item 
     */

    public int getPrice() {
        return price;
    }

    /* 
     * returns the amount of calories of the chosen item 
     * @return the amount of calories of the chosen item 
     */

    public float getCalories() {
        return calories;
    }

    /* 
     * returns the quantity of the chosen item 
     * @return the quantity of the chosen item 
     */

    public int getQuantity() {
        return quantity;
    }
}