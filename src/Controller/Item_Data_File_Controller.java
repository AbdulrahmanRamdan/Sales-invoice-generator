package Controller;

import Model.Invoice_Table_Model;
import Model.Item_Table_Model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Item_Data_File_Controller {
    private File file=null;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void add_Item_Data(Item_Table_Model item){
        try{
            FileWriter writer=new FileWriter(this.file,true);
            writer.append(String.valueOf(item.getInvoice_Number()));
            writer.append(',');
            writer.append(String.valueOf(item.getItem_Name()));
            writer.append(',');
            writer.append(String.valueOf(item.getItem_Price()));
            writer.append(',');
            writer.append(String.valueOf(item.getItem_Count()));
            writer.append("\n");
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Item_Table_Controller read_Items_Data() throws IOException {
        Item_Table_Controller item_table_controller = null;
        ArrayList<Item_Table_Model> items=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader(this.file));
        try {
            String line="";
            while ((line=reader.readLine())!=null){
                items.add(split_Line(line));
            }
            item_table_controller=new Item_Table_Controller(items);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reader.close();
        if(item_table_controller!=null)
            return item_table_controller;
        else
            return null;
    }
    private Item_Table_Model split_Line(String line) throws ParseException {
        String []attribute=line.split(",");
        return new Item_Table_Model(Integer.parseInt(attribute[0]),attribute[1],Double.valueOf(attribute[2]),Integer.parseInt(attribute[3]));
    }
    public void clear_File() throws FileNotFoundException {
        PrintWriter writer=new PrintWriter(file);
        writer.print("");writer.close();
    }
}
