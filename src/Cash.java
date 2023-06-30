/*
 * Cash represents a user's cash at hand. It contains the cash value, quantity
 * and total number of cash for the user. 
 */

public class Cash{
    //initialize variables 
    private int value;
    private int quantity = 0;
    private int total;
    
    /* 
     * creates a Cash object for each cash value
     * The quantity for the cash is initialized as 0 
     * @param value refers to cash value  
    */

    public Cash (int value) {
        this.value = value;
    }

    //getter methods 

    /*
     * returns the value for the given cash
     * @return the value for the given cash
     */

    public int getValue() {
        return value;
    }

    /*
     * returns the quantity or denomination for the given cash 
     * @return the quantity or denomination for the given cash 
     */

    public int getQuantity() {
        return quantity;
    }

    /*
     * returns total cash at hand
     * @return total cash at hand
     */

    public int getTotal() {
        return total;
    }

    /*
     * sets the quantity of cash object 
     * computes for the total cash of user at hand
     * @param quantity refers to quantity or denomination of cash
     */
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;

        this.total = getValue() * getQuantity();
    }
}