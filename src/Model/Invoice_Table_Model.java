package Model;

import java.util.ArrayList;
import java.util.Date;

public class Invoice_Table_Model {
    private int invoice_Number;
    private String invoice_Date;
    private String customer_Name;
    private double invoice_Total;
    private ArrayList<Item_Table_Model> items=new ArrayList<>(); ;

    public void add_Items(Item_Table_Model item){
        this.items.add(item);
    }

    public ArrayList<Item_Table_Model> getItems() {
        if(items==null)
            items=new ArrayList<>();
        return items;
    }

    public void setItems(ArrayList<Item_Table_Model> items) {
        this.items = items;
    }

    public Invoice_Table_Model(int invoice_Number, String invoice_Date, String customer_Name){
        this.invoice_Number=invoice_Number;
        this.invoice_Date=invoice_Date;
        this.customer_Name=customer_Name;
    }

    public int getInvoice_Number() {
        return invoice_Number;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.invoice_Number = invoice_Number;
    }

    public String getInvoice_Date() {
        return invoice_Date;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }


    public double getInvoice_Total() {
        invoice_Total=0;
        for(int i=0;i<items.size();i++){
            invoice_Total+=items.get(i).getItem_Total();
        }
        return invoice_Total;
    }


}
