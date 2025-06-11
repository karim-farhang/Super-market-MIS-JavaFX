package sample;

public class TableSellProduct {
    int ID;
    String NAME;
    double TOTAL;
    String UNIT;
    double PRICE;
    String CUSTOMER_NAME;

    public TableSellProduct(int ID, String NAME, double TOTAL, String UNIT, double PRICE, String CUSTOMER_NAME) {
        this.ID = ID;
        this.NAME = NAME;
        this.TOTAL = TOTAL;
        this.UNIT = UNIT;
        this.PRICE = PRICE;
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public String getUNIT() {
        return UNIT;
    }

    public double getPRICE() {
        return PRICE;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }
}