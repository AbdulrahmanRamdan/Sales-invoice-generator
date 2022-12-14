package Model;

public class Item_Table_Model {
    private int item_Number;
    private String item_Name;
    private double item_Price;
    private int item_Count;
    private double item_Total;
    private int invoice_Number;

    public int getInvoice_Number() {
        return invoice_Number;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.invoice_Number = invoice_Number;
    }

    public Item_Table_Model(int invoice_Number, String item_Name, double item_Price, int item_Count ) {
        this.item_Name = item_Name;
        this.item_Price = item_Price;
        this.item_Count = item_Count;
        this.invoice_Number=invoice_Number;
        this.setItem_Total(this.item_Count * this.item_Price);
    }

    public int getItem_Number() {
        return item_Number;
    }

    public void setItem_Number(int item_Number) {
        this.item_Number = item_Number;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public double getItem_Price() {
        return item_Price;
    }

    public void setItem_Price(double item_Price) {
        this.item_Price = item_Price;
    }

    public int getItem_Count() {
        return item_Count;
    }

    public void setItem_Count(int item_Count) {
        this.item_Count = item_Count;
    }

    public double getItem_Total() {
        return item_Total;
    }

    public void setItem_Total(double item_Total) {
        this.item_Total = item_Total;
    }


}
