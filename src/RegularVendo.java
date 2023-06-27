
public class RegularVendo {

    private Item[] item;
    
    public RegularVendo() {
        this.item = new Item[8];
    }

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
    
    public boolean removeItem(int slot) {

        if (item[slot] != null){
            item[slot] = null;
            return true;
        }
       

        return false;
    }


    public Item[] getItems() {
        
        if (item != null)
            return item;
        
        return null;
    }

    public void restockItem(int quantity, Item item) {

        item.addQuantity(quantity);
    }

    public void setItemPrice(int price, int slot) {

        getItem(slot).setPrice(price);
    }

    public Item getItem(int slot) {
        return item[slot];
    }

    public int getItemPrice(int slot) {
        return item[slot].getPrice();
    }
  
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
