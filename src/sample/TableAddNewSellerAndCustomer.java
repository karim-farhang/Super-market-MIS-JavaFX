package sample;

public class TableAddNewSellerAndCustomer {
    int ID;
    String NAME;
    String ADDRESS;
    String NUMBER;
    String NUMBER2;
    String RAG_DATE;

    public TableAddNewSellerAndCustomer(int ID, String NAME, String ADDRESS, String NUMBER, String NUMBER2, String RAG_DATE) {
        this.ID = ID;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.NUMBER = NUMBER;
        this.NUMBER2 = NUMBER2;
        this.RAG_DATE = RAG_DATE;
    }

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public String getNUMBER2() {
        return NUMBER2;
    }

    public String getRAG_DATE() {
        return RAG_DATE;
    }
}
