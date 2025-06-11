package sample;

public class TableUpdateProduct {
    int ID;
    String NAME;
    String CATAGORY;
    String TOTAL;
    String UNIT;
    String PRICE;
    String IMPORT_DATE;
    String EXPIRE_DATE;
    String SELLER_NAME;

    public TableUpdateProduct(int ID, String NAME, String CATAGORY, String TOTAL, String UNIT, String PRICE, String IMPORT_DATE, String EXPIRE_DATE, String SELLER_NAME) {
        this.ID = ID;
        this.NAME = NAME;
        this.CATAGORY = CATAGORY;
        this.TOTAL = TOTAL;
        this.UNIT = UNIT;
        this.PRICE = PRICE;
        this.IMPORT_DATE = IMPORT_DATE;
        this.EXPIRE_DATE = EXPIRE_DATE;
        this.SELLER_NAME = SELLER_NAME;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getCATAGORY() {
        return CATAGORY;
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

    public String getEXPIRE_DATE() {
        return EXPIRE_DATE;
    }

    public String getSELLER_NAME() {
        return SELLER_NAME;
    }
}