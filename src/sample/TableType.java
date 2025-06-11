package sample;

public class TableType {

    int ID;
    String Product_name;
    String Catagory;
    String Total;
    String Unit;
    String Price;
    String Import_date;
    String Expire_date;
    String Sell_name;

    String Sell_address;
    String Sell_number;
    String Sell_number2;
    String Sell_rag_date;

    //customar

    public TableType(String sell_name, String sell_address, String sell_number, String sell_number2) {
        Sell_name = sell_name;
        Sell_address = sell_address;
        Sell_number = sell_number;
        Sell_number2 = sell_number2;
    }

    // pay_Seller_table
    public TableType(String name, String total, String unit, String pirce, String import_date) {
        this.Product_name = name;
        this.Total = total;
        this.Unit = unit;
        this.Price = pirce;
        this.Import_date = import_date;
    }


    //import table conseractor
    public TableType(String product_name, String catagory, String total, String unit, String price, String import_date, String expire_date, String sell_name) {
        Product_name = product_name;
        Catagory = catagory;
        Total = total;
        Unit = unit;
        Price = price;
        Import_date = import_date;
        Expire_date = expire_date;
        Sell_name = sell_name;
    }


    public TableType() {
    }


    //Sell_product
    public TableType(int id, String sell_name, String sell_address, String sell_number, String sell_number2, String sell_rag_date) {
        ID = id;
        Sell_name = sell_name;
        Sell_address = sell_address;
        Sell_number = sell_number;
        Sell_number2 = sell_number2;
        Sell_rag_date = sell_rag_date;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImport_date() {
        return Import_date;
    }

    public void setImport_date(String import_date) {
        Import_date = import_date;
    }

    public String getExpire_date() {
        return Expire_date;
    }

    public void setExpire_date(String expire_date) {
        Expire_date = expire_date;
    }

    public String getSell_name() {
        return Sell_name;
    }

    public void setSell_name(String sell_name) {
        Sell_name = sell_name;
    }

    public String getSell_address() {
        return Sell_address;
    }

    public void setSell_address(String sell_address) {
        Sell_address = sell_address;
    }

    public String getSell_number() {
        return Sell_number;
    }

    public void setSell_number(String sell_number) {
        Sell_number = sell_number;
    }

    public String getSell_number2() {
        return Sell_number2;
    }

    public void setSell_number2(String sell_number2) {
        Sell_number2 = sell_number2;
    }

    public String getSell_rag_date() {
        return Sell_rag_date;
    }

    public void setSell_rag_date(String sell_rag_date) {
        Sell_rag_date = sell_rag_date;
    }
}