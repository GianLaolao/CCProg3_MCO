public class MoneyBox {
    
    private Cash onePeso = new Cash(1);
    private Cash fivePeso = new Cash(5);
    private Cash tenPeso = new Cash(10);
    private Cash twentyPeso = new Cash(20);
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
    public void setTwentyPeso(int quantity) {
        this.twentyPeso.setQuantity(quantity);
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
    public Cash getTwentyPeso() {
        return twentyPeso;
    }
    public Cash getFiftyPeso() {
        return fiftyPeso;
    }
    public Cash getHundredPeso() {
        return hundredPeso;
    }
}
