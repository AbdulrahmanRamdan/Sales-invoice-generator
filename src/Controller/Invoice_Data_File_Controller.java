package Controller;

import Model.Invoice_Table_Model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice_Data_File_Controller {
    private File file=null;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void add_Invoice_Data(Invoice_Table_Model invoice){
        try{
            FileWriter writer=new FileWriter(this.file,true);
            writer.append(String.valueOf(invoice.getInvoice_Number()));
            writer.append(',');
            writer.append(String.valueOf(invoice.getInvoice_Date()));
            writer.append(',');
            writer.append(String.valueOf(invoice.getCustomer_Name()));
            writer.append("\n");
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Invoice_Table_Controller read_Invoice_Data() throws IOException {
        Invoice_Table_Controller invoice_table_controller = null;
        ArrayList<Invoice_Table_Model>invoiceis=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(this.file));
        try {
            String line="";
            while ((line=reader.readLine())!=null){
                invoiceis.add(split_Line(line));
            }
            invoice_table_controller=new Invoice_Table_Controller(invoiceis);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reader.close();
        if(invoice_table_controller!=null)
            return invoice_table_controller;
        else
            return null;
    }
    private Invoice_Table_Model split_Line(String line) throws ParseException {
        String []attribute=line.split(",");
        return new Invoice_Table_Model(Integer.parseInt(attribute[0]), attribute[1],attribute[2]);
    }
    public void clear_File() throws FileNotFoundException {
        PrintWriter writer=new PrintWriter(file);
        writer.print("");writer.close();
    }
}