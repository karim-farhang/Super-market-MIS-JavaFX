package sample;

import java.sql.Date;

public class TablePrintBill {
    int BILL_NUMBER;
    Date DATE;
    int ID;
    String NAME;
    double TOTAL;
    String UNIT;
    double PRICE;
    double TOTAL_PRICE;

    public TablePrintBill(int BILL_NUMBER, Date DATE, int ID, String NAME, double TOTAL, String UNIT, double PRICE, double TOTAL_PRICE) {
        this.BILL_NUMBER = BILL_NUMBER;
        this.DATE = DATE;
        this.ID = ID;
        this.NAME = NAME;
        this.TOTAL = TOTAL;
        this.UNIT = UNIT;
        this.PRICE = PRICE;
        this.TOTAL_PRICE = TOTAL_PRICE;
    }

    public int getBILL_NUMBER() {
        return BILL_NUMBER;
    }

    public Date getDATE() {
        return DATE;
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

    public double getTOTAL_PRICE() {
        return TOTAL_PRICE;
    }
}
