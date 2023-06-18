public class MoneyBox {
    
    private Cash onePeso = new Cash(1);
    private Cash fivePeso = new Cash(5);
    private Cash tenPeso = new Cash(10);
    private Cash fiftyPeso = new Cash(50);
    private Cash hundredPeso = new Cash(100);

    public void setOnePeso(int quantity) {
        this.onePeso.setQuantity(quantity);
    }
    public void setFivePeso(int quantity) {
        this.fivePeso.setQuantity(quantity);
    }
    public void setTenPeso(int quantity) {
        this.tenPeso.setQuantity(quantity);
    }
    public void setFiftyPeso(int quantity) {
        this.fiftyPeso.setQuantity(quantity);
    }
    public void setHundredPeso(int quantity) {
        this.hundredPeso.setQuantity(quantity);
    }

    public Cash getOnePeso() {
        return onePeso;
    }
    public Cash getFivePeso() {
        return fivePeso;
    }
    public Cash getTenPeso() {
        return tenPeso;
    }
    public Cash getFiftyPeso() {
        return fiftyPeso;
    }
    public Cash getHundredPeso() {
        return hundredPeso;
    }


    public void collectPayment(int payment) {
        //collect payment and distribute each denomination
    }

    public int giveChange(int payment, int price) {
        //calculate change and return change

        return 0;
    }

    public int collectProft() {

        int earning;

        earning = onePeso.getTotal() + fivePeso.getTotal() + tenPeso.getTotal() + fiftyPeso.getTotal() + hundredPeso.getTotal();

        onePeso.setQuantity(0);
        fivePeso.setQuantity(0);
        tenPeso.setQuantity(0);
        fiftyPeso.setQuantity(0);
        hundredPeso.setQuantity(0);


        return earning;
    }
}
