package Model;

import Controller.Invoice_Data_File_Controller;
import Controller.Item_Data_File_Controller;
import Controller.Merge_Data;

import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
    private Invoice_Data_File_Controller invoiceDataFileController=new Invoice_Data_File_Controller();
    private Item_Data_File_Controller itemDataFileController=new Item_Data_File_Controller();
    private void writeFile(ArrayList<Invoice_Table_Model>invoicies){
        for(int i=0;i<invoicies.size();i++){
            invoiceDataFileController.add_Invoice_Data(invoicies.get(i));
            for(int j=0;j<invoicies.get(i).getItems().size();j++){
             itemDataFileController.add_Item_Data(invoicies.get(i).getItems().get(j));
            }
        }
    }
    private ArrayList<Invoice_Table_Model>readFile() throws IOException {
        return new Merge_Data().merge_Invoice_Items().getInvoice_Table();
    }
    public static void main(String[]arg) throws IOException {
        FileOperations FO=new FileOperations();
        ArrayList<Invoice_Table_Model>invoicies=FO.readFile();
        for(int i=0;i<invoicies.size();i++){
            Invoice_Table_Model in=invoicies.get(i);
            System.out.println("Invoice"+in.getInvoice_Number()+"Num");
            System.out.println("{");
            System.out.println(in.getInvoice_Date()+","+in.getCustomer_Name());
            for(int j=0;j<in.getItems().size();j++){
                System.out.println(in.getItems().get(j).getItem_Name()+","+in.getItems().get(j).getItem_Price()+","+in.getItems().get(j).getItem_Count());
            }
            System.out.println("}");
            System.out.println();
        }
    }

}