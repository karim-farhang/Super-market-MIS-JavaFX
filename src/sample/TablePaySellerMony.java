package sample;

public class TablePaySellerMony {
    int ID;
    String NAME;
    String TOTAL;
    String UNIT;
    String PRICE;
    String IMPORT_DATE;

    public TablePaySellerMony(int ID, String NAME, String TOTAL, String UNIT, String PRICE, String IMPORT_DATE) {
        this.ID = ID;
        this.NAME = NAME;
        this.TOTAL = TOTAL;
        this.UNIT = UNIT;
        this.PRICE = PRICE;
        this.IMPORT_DATE = IMPORT_DATE;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getTOTAL() {
        return TOTAL;
    }

    public String getUNIT() {
        return UNIT;
    }

    public String getPRICE() {
        return PRICE;
    }

    public String getIMPORT_DATE() {
        return IMPORT_DATE;
    }
}
