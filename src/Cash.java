public class Cash{

    private int value;
    private int quantity = 0;
    private int total;

    public Cash (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getTotal() {
        return total;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

        this.total = getValue() * getQuantity();
    }
}