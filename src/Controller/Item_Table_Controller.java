package Controller;

import Model.Item_Table_Model;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Item_Table_Controller  {
    private static String[]columns_Names={"Item Number","Item Name", "Item Price", "Count", "Item Total"};
    private ArrayList<Item_Table_Model>items_table_models;
    public Item_Table_Controller(ArrayList<Item_Table_Model>items_table_models){
        this.items_table_models=items_table_models;
    }

    public ArrayList<Item_Table_Model> getItems_table_models() {
        return items_table_models;
    }


    public int getRowCount() {
        if(items_table_models==null)
            items_table_models=new ArrayList<>();
        return items_table_models.size();
    }

    public int getColumnCount() {
        return columns_Names.length;
    }


    public void removeRow(int row) {
        items_table_models.remove(row);
    }


    public String getColumnName(int column) {
        return columns_Names[column];
    }

    public Object getValueAt(int row,int column){
        Item_Table_Model item_table_model=items_table_models.get(row);
        if(column==0)
            return item_table_model.getItem_Number();
        else if(column==1)
            return item_table_model.getItem_Name();
        else if(column==2)
            return item_table_model.getItem_Price();
        else if(column==3)
            return item_table_model.getItem_Count();
        else if(column==4)
            return item_table_model.getItem_Total();
        return -1;
    }
}
