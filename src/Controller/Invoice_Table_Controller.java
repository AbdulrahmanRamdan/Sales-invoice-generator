package Controller;

import Model.Invoice_Table_Model;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Invoice_Table_Controller  {
    private static String [] columns_Header={"Invoice Number","Invoice Date","Customer Name","Total"};
    private ArrayList<Invoice_Table_Model> invoice_Table=new ArrayList<>();

    public void setInvoice_Table(ArrayList<Invoice_Table_Model> invoice_Table) {
        this.invoice_Table = invoice_Table;
    }
    public Invoice_Table_Controller(){}
    public Invoice_Table_Controller(ArrayList<Invoice_Table_Model> invoice_Table){
        this.invoice_Table=invoice_Table;
    }

    public ArrayList<Invoice_Table_Model> getInvoice_Table() {
        return invoice_Table;
    }

    public int getRowCount() {
        if (invoice_Table==null)
            invoice_Table=new ArrayList<>();
        return invoice_Table.size();
    }

    public void removeRow(int row) {
        invoice_Table.remove(row);
    }

    public int getColumnCount() {
        return columns_Header.length;
    }

    public String getColumnName(int column) {
        return columns_Header[column];
    }

    public Object getValueAt(int row, int column) {
        Invoice_Table_Model invoice_table_model=invoice_Table.get(row);
        if(column==0)
            return invoice_table_model.getInvoice_Number();
        else if(column==1)
            return invoice_table_model.getInvoice_Date();
        else if(column==2)
            return invoice_table_model.getCustomer_Name();
        else if(column==3)
            return invoice_table_model.getInvoice_Total();
        else
            return -1;
    }
}
