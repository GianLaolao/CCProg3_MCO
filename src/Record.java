public class Record {
    private Item item;
    private int sold = 0;
    private int startingInventory;

    public Record(Item item) {
        this.item = item;
        setStartingInventory();
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setStartingInventory() {
        this.startingInventory = item.getQuantity();
    }

    public int getSold() {
        return sold;
    }

    public int getStartingInventory() {
        return startingInventory;
    }
}
