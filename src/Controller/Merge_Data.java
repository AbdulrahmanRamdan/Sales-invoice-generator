package Controller;

import Model.Invoice_Table_Model;
import Model.Item_Table_Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Merge_Data {
    Invoice_Data_File_Controller invoiceDataFileController=new Invoice_Data_File_Controller();
    Item_Data_File_Controller itemDataFileController=new Item_Data_File_Controller();

    public Merge_Data(){

    }
    public Merge_Data(File invoice_file,File item_file){
        invoiceDataFileController.setFile(invoice_file);
        itemDataFileController.setFile(item_file);
    }
    public Invoice_Table_Controller merge_Invoice_Items() throws IOException {
        Invoice_Table_Controller invoice_table_controller;
        invoice_table_controller=invoiceDataFileController.read_Invoice_Data();
        Item_Table_Controller item_table_controller=itemDataFileController.read_Items_Data();
        for(int i=0;i<invoice_table_controller.getInvoice_Table().size();i++){
             for(int j=0;j<item_table_controller.getItems_table_models().size();j++){
                if(item_table_controller.getItems_table_models().get(j).getInvoice_Number()==invoice_table_controller.getInvoice_Table().get(i).getInvoice_Number()){
                    Item_Table_Model item=item_table_controller.getItems_table_models().get(j);
                    invoice_table_controller.getInvoice_Table().get(i).add_Items(item);
                }
            }
        }
        return invoice_table_controller;
    }
    public void clear_Restore(Invoice_Table_Controller invoice_table_controller) throws FileNotFoundException {
        if(invoice_table_controller.getInvoice_Table().size()>0){
        invoiceDataFileController.clear_File();
        itemDataFileController.clear_File();
        for(int i=0; i<invoice_table_controller.getInvoice_Table().size();i++){
            invoiceDataFileController.add_Invoice_Data(invoice_table_controller.getInvoice_Table().get(i));
            for(int j=0; j<invoice_table_controller.getInvoice_Table().get(i).getItems().size();j++){
                itemDataFileController.add_Item_Data(invoice_table_controller.getInvoice_Table().get(i).getItems().get(j));
            }
        }

    }
    }
}
