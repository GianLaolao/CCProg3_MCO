public class MoneyBox {
    
    private int onePeso;
    private int fivePeso;
    private int tenPeso;
    private int fiftyPeso;
    private int hundredPeso;

    public void setOnePeso(int onePeso) {
        this.onePeso = onePeso;
    }
    public void setFivePeso(int fivePeso) {
        this.fivePeso = fivePeso;
    }
    public void setTenPeso(int tenPeso) {
        this.tenPeso = tenPeso;
    }
    public void setFiftyPeso(int fiftyPeso) {
        this.fiftyPeso = fiftyPeso;
    }
    public void setHundredPeso(int hundredPeso) {
        this.hundredPeso = hundredPeso;
    }

    public int getOnePeso() {
        return onePeso;
    }
    public int getFivePeso() {
        return fivePeso;
    }
    public int getTenPeso() {
        return tenPeso;
    }
    public int getFiftyPeso() {
        return fiftyPeso;
    }
    public int getHundredPeso() {
        return hundredPeso;
    }

    public int collectMoney() {

        int earning;

        earning = (getOnePeso()) + (getFivePeso() * 5) + (getTenPeso() * 10) + (getFiftyPeso() * 50) + (getHundredPeso() * 100);

        return earning;
    }
}
