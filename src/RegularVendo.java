
public class RegularVendo {

    private Item[] item = new Item[8];
        
    public boolean addItem(Item product) {
        if (item.length < 8) {
            for(int i = 0; i < item.length; i++){
                if (item[i] == null){
                    item[i] = product;
                }
            }
            return true;
        }
        return false;   
    }
    
    public boolean removeItem(int slot) {

        //TODO

        return false;
    }


    public Item[] getItems() {
        
        if (item.length > 0)
            return item;
        
        return null;
    }

    public boolean restockItem(int quantity, int slot) {

        Item item = getItem(slot);

        if (10 - item.getQuantity() >= quantity) {
            item.addQuantity(quantity);
            return true;
        }
            
        return false;
    }

    public Item getItem(int slot) {
        return item[slot];
    }
  
    public Item dispenseItem(int slot, int quantity) {

        //TODO
        //code for successful dispense of item

            // return true;

        
        return null;
    }

}
