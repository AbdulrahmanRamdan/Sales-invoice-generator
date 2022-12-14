package View;
import Controller.Invoice_Table_Controller;
import Controller.Merge_Data;
import Model.Invoice_Table_Model;
import Model.Item_Table_Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class Frame extends JFrame implements ActionListener, ListSelectionListener {

    private Invoice_Table_Controller invoice_table_controller;
    public int invoice_Selected_Row=-1;

    private JButton create_New_Invoice_btn,delete_Invoice_btn,insert_New_Item_btn,delete_Item_btn;
    private JLabel invoice_Number_Label,invoice_Number_Data_Label,invoice_Data_Label,customer_Name_Label,invoice_Total_Label,invoice_Total_Data_Label,invoice_Table_Label;
    private JLabel j1,j2;private JTable invoice_Table,invoice_Items_Table;
    private JScrollPane invoice_Table_ScrollPane,invoice_Items_Table_ScrollPane;
    private JTextField invoice_Data_TF,customer_Name_TF;
    private JMenuBar menuBar;private JMenu file_Menu;private JMenuItem save_Menu_Item;private JMenuItem load_Menu_Item;
    private static String create_Invoice_Command="create_New_Invoice";
    private static String delete_Invoice_Command="delete_Invoice_Invoice";
    private static String insert_Item_Command="insert_New_Item";
    private static String delete_Item_Command="delete_Item";
    private static String dialog="Dialog";
    private static String invoice_Number_Access_name="Invoice Number";
    private static String invoice_Data_TF_Access_name="Invoice Data";
    private static String customer_Name_TF_Access_name="Customer Name";

    private JDialog invoice_Dialog;
    private JLabel customer_Name_Label_Dialog,invoice_Number_Label_Dialog;
    private JTextField customer_Name_TF_Dialog,invoice_Number_TF_Dialog;
    private JButton ok_Invoice_BTN,cancel_Invoice_BTN;

    private void initialize_Invoice_Dialog(){
        invoice_Dialog=new JDialog();
        invoice_Number_Label_Dialog=new JLabel("Invoice Number :");
        customer_Name_Label_Dialog=new JLabel("Customer Name : ");
        invoice_Number_TF_Dialog=new JTextField(30);
        customer_Name_TF_Dialog=new JTextField(30);
        ok_Invoice_BTN=new JButton("Ok");
        cancel_Invoice_BTN=new JButton("Cancel");
        ok_Invoice_BTN.setActionCommand(create_Invoice_Command+dialog);
        cancel_Invoice_BTN.setActionCommand("Invoice Cancel");
        ok_Invoice_BTN.addActionListener(this);
        cancel_Invoice_BTN.addActionListener(this);
        invoice_Dialog.setLayout(new GridLayout(3,2));
        invoice_Dialog.add(invoice_Number_Label_Dialog);invoice_Dialog.add(invoice_Number_TF_Dialog);
        invoice_Dialog.add(customer_Name_Label_Dialog);invoice_Dialog.add(customer_Name_TF_Dialog);
        invoice_Dialog.add(ok_Invoice_BTN);invoice_Dialog.add(cancel_Invoice_BTN);
        invoice_Dialog.pack();
    }

        private void create_invoice() {
        String invoice_Number = invoice_Number_TF_Dialog.getText();
        String customer_Name = customer_Name_TF_Dialog.getText();
            LocalDate localDate=LocalDate.now();
        String date=localDate.getDayOfMonth()+"/"+localDate.getMonthValue()+"/"+String.valueOf(localDate.getYear());
        Invoice_Table_Model invoice_table_model = new Invoice_Table_Model(Integer.parseInt(invoice_Number), date, customer_Name);
        invoice_table_controller.getInvoice_Table().add(invoice_table_model);
        invoice_dtm.addRow(new Object[]{invoice_table_model.getInvoice_Number(), invoice_table_model.getInvoice_Date(),
                invoice_table_model.getCustomer_Name(), invoice_table_model.getInvoice_Total()});

        }

        private void delete_Invoice(){
            invoice_table_controller.getInvoice_Table().remove(invoice_Selected_Row);
            items_dtm.setRowCount(0);
            invoice_Number_Data_Label.setText("");
            invoice_Data_TF.setText("");
            customer_Name_TF.setText("");
            invoice_Total_Data_Label.setText("");
            invoice_dtm.removeRow(invoice_Selected_Row);
            invoice_dtm.fireTableDataChanged();
        }

        private void insert_Item(){
        int invoice_Number=invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getInvoice_Number();
        int num=invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getItems().size()+1;
        Item_Table_Model item_table_model=new Item_Table_Model(invoice_Number,item_Name_TF_Dialog.getText(),Double.valueOf(item_price_TF_Dialog.getText()),Integer.parseInt(item_count_Tf_Dialog.getText()));
        item_table_model.setItem_Number(num);
        invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).add_Items(item_table_model);
        items_dtm.addRow(new Object[]{item_table_model.getItem_Number(), item_table_model.getItem_Name(), item_table_model.getItem_Price(), item_table_model.getItem_Count(), item_table_model.getItem_Total()});
        invoice_Total_Data_Label.setText(String.valueOf(invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getInvoice_Total()));
        invoice_dtm.setValueAt(invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getInvoice_Total(),invoice_Selected_Row,3);
        }
        private void delete_Item(){
         int selected_Row=invoice_Items_Table.getSelectedRow();
         invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getItems().remove(selected_Row);
         double total=invoice_table_controller.getInvoice_Table().get(invoice_Selected_Row).getInvoice_Total();
         invoice_dtm.setValueAt(total,invoice_Selected_Row,3);
         items_dtm.removeRow(selected_Row);
        }


        private JDialog item_Dialog;
        private JLabel item_Name_Label_Dialog, item_price_Label_Dialog, item_count_Label_Dialog;
        private JTextField item_Name_TF_Dialog, item_price_TF_Dialog, item_count_Tf_Dialog;
        private JButton ok_Item_BTN, cancel_Item_BTN;

        private void initialize_Item_Dialog () {
            item_Dialog = new JDialog();
            item_Name_Label_Dialog = new JLabel("Item Name : ");
            item_price_Label_Dialog = new JLabel("Item Price : ");
            item_count_Label_Dialog = new JLabel("Item Count : ");
            item_Name_TF_Dialog = new JTextField(30);
            item_price_TF_Dialog = new JTextField(30);
            item_count_Tf_Dialog = new JTextField(30);
            ok_Item_BTN = new JButton("Ok");
            cancel_Item_BTN = new JButton("Cancel");
            ok_Item_BTN.setActionCommand(insert_Item_Command + dialog);
            cancel_Item_BTN.setActionCommand("Item Cancel");
            ok_Item_BTN.addActionListener(this);
            cancel_Item_BTN.addActionListener(this);
            item_Dialog.setLayout(new GridLayout(4, 2));
            item_Dialog.add(item_Name_Label_Dialog);
            item_Dialog.add(item_Name_TF_Dialog);
            item_Dialog.add(item_price_Label_Dialog);
            item_Dialog.add(item_price_TF_Dialog);
            item_Dialog.add(item_count_Label_Dialog);
            item_Dialog.add(item_count_Tf_Dialog);
            item_Dialog.add(ok_Item_BTN);
            item_Dialog.add(cancel_Item_BTN);
            item_Dialog.pack();
        }

        public Frame(String frame_Title)  {
            this.setTitle(frame_Title);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initialize_Frame_Components();
            invoice_table_controller = new Invoice_Table_Controller();
            invoice_dtm.setColumnIdentifiers(new String[]{
                    "Invoice Number", "Invoice Date", "Customer Name", "Total"
            });
        items_dtm.setColumnIdentifiers(new String[]{
                "Item Number", "Item Name", "Item Price", "Item Count", "Total"
        });
            invoice_Selected_Row = invoice_Table.getSelectedRow();
        }

        private void initialize_Frame_Components () {
            initialize_Menu_Bar();
            initialize_Buttons();
            initialize_Labels();
            initialize_Text_Field();
            initialize_Tables();
            organize_Layout();
            this.pack();
            initialize_Invoice_Dialog();
            initialize_Item_Dialog();
        }


        private void loadFileMenuItemActionPerformed (java.awt.event.ActionEvent evt){
            JOptionPane.showMessageDialog(this, "Please select Invoice header file", "Invoice Header", JOptionPane.WARNING_MESSAGE);
            JFileChooser fc = new JFileChooser();
            File invoice_File=null;
            File item_File=null;
            int option = fc.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                 invoice_File = fc.getSelectedFile();
            }
            JOptionPane.showMessageDialog(this, "Please select Invoice lines file", "Invoice Lines", JOptionPane.WARNING_MESSAGE);
            fc=new JFileChooser();
             option = fc.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                item_File = fc.getSelectedFile();
            }
            try {
                invoice_table_controller = new Merge_Data(invoice_File,item_File).merge_Invoice_Items();
                for (int i = 0; i < invoice_table_controller.getInvoice_Table().size(); i++) {
                    Invoice_Table_Model invoice_table_model = invoice_table_controller.getInvoice_Table().get(i);
                    invoice_dtm.addRow(new Object[]{invoice_table_model.getInvoice_Number(), invoice_table_model.getInvoice_Date(),
                            invoice_table_model.getCustomer_Name(), invoice_table_model.getInvoice_Total()});
                }
                JOptionPane.showMessageDialog(this,"Data loaded successfully","Load Data", JOptionPane.INFORMATION_MESSAGE);


            } catch (Exception ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, "Cannot Load files", ex);
            }
        }

        private void saveFileMenuItemActionPerformed(ActionEvent evt) {
        try {
            new Merge_Data().clear_Restore(invoice_table_controller);
            JOptionPane.showMessageDialog(this,"Data saved successfully","Save Updates", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, "Cannot save files", ex);
        }
    }

        public static void main (String args[])  {
            new Frame("Sales Invoice Generator").setVisible(true);
        }
        private static DefaultTableModel  invoice_dtm = new DefaultTableModel(0,0 );
        DefaultTableModel items_dtm = new DefaultTableModel(0,0 );

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getActionCommand().equals(create_Invoice_Command)) {
                invoice_Dialog.setVisible(true);
            } else if (e.getActionCommand().equals(delete_Invoice_Command)) {
                delete_Invoice();
                JOptionPane.showMessageDialog(this,"Invoice deleted successfully","Deleted", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getActionCommand().equals(insert_Item_Command)) {
                item_Dialog.setVisible(true);
            } else if (e.getActionCommand().equals(delete_Item_Command)) {
                delete_Item();
                JOptionPane.showMessageDialog(this,"Item deleted successfully","Deleted", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getActionCommand().equals(create_Invoice_Command + dialog)) {
                create_invoice();
                JOptionPane.showMessageDialog(this,"Invoice created successfully","Create Invoice", JOptionPane.INFORMATION_MESSAGE);
                invoice_Dialog.dispose();
            } else if (e.getActionCommand().equals("Invoice Cancel")) {
                invoice_Dialog.dispose();
            } else if (e.getActionCommand().equals(insert_Item_Command + dialog)) {
                insert_Item();
                JOptionPane.showMessageDialog(this,"Item inserted successfully","Insert Item", JOptionPane.INFORMATION_MESSAGE);
                item_Dialog.dispose();
            } else if (e.getActionCommand().equals("Item Cancel")) {
                item_Dialog.dispose();
            }
        }
        @Override
        public void valueChanged (ListSelectionEvent e){

        }
        private void initialize_Buttons () {
            create_New_Invoice_btn = new JButton();
            delete_Invoice_btn = new JButton();
            insert_New_Item_btn = new JButton();
            delete_Item_btn = new JButton();
            create_New_Invoice_btn.addActionListener(this);
            delete_Invoice_btn.addActionListener(this);
            insert_New_Item_btn.addActionListener(this);
            delete_Item_btn.addActionListener(this);
            create_New_Invoice_btn.setActionCommand(create_Invoice_Command);
            delete_Invoice_btn.setActionCommand(delete_Invoice_Command);
            insert_New_Item_btn.setActionCommand(insert_Item_Command);
            delete_Item_btn.setActionCommand(delete_Item_Command);
            create_New_Invoice_btn.setText("Create New Invoice");
            delete_Invoice_btn.setText("Delete Invoice");
            insert_New_Item_btn.setText("Insert New Item");
            delete_Item_btn.setText("Delete Item");
        }
        private void initialize_Labels () {
            invoice_Number_Label = new JLabel("Invoice Number :");
            invoice_Data_Label = new JLabel("Invoice Data");
            customer_Name_Label = new JLabel("Customer Name");
            invoice_Total_Label = new JLabel("Invoice total");
            invoice_Table_Label = new JLabel("Invoice Table");
            invoice_Number_Data_Label = new JLabel();
            invoice_Total_Data_Label = new JLabel();
            j1 = j2 = new JLabel();
            invoice_Number_Data_Label.getAccessibleContext().setAccessibleName(invoice_Number_Access_name);
        }
        private void initialize_Tables () {
            invoice_Table = new JTable();
            invoice_Table.setCellSelectionEnabled(false);
            invoice_Table.setRowSelectionAllowed(true);
            invoice_Items_Table = new JTable();
            invoice_Table_ScrollPane = new JScrollPane();
            invoice_Items_Table_ScrollPane = new JScrollPane();
            invoice_Table.getSelectionModel().addListSelectionListener(this);
            invoice_Items_Table.getSelectionModel().addListSelectionListener(this);
            invoice_Table.setModel(invoice_dtm);
            invoice_Items_Table.setModel(items_dtm);
            invoice_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    invoice_Selected_Row=invoice_Table.getSelectedRow();
                    try {
                        Invoice_Table_Model invoice_table_model1 = invoice_table_controller.getInvoice_Table().get(invoice_Table.getSelectedRow());
                        invoice_Number_Data_Label.setText(String.valueOf(invoice_table_model1.getInvoice_Number()));
                        invoice_Data_TF.setText(String.valueOf(invoice_table_model1.getInvoice_Date()));
                        customer_Name_TF.setText(String.valueOf(invoice_table_model1.getCustomer_Name()));
                        invoice_Total_Data_Label.setText(String.valueOf(invoice_table_model1.getInvoice_Total()));
                        items_dtm.setRowCount(0);
                        if(invoice_table_model1.getItems().size()!=0) {
                                for (int i = 0; i < invoice_table_model1.getItems().size(); i++) {
                                    Item_Table_Model item = invoice_table_model1.getItems().get(i);
                                    item.setItem_Number(i+1);
                                    items_dtm.addRow(new Object[]{item.getItem_Number(), item.getItem_Name(), item.getItem_Price(), item.getItem_Count(), item.getItem_Total()});
                                }
                                JOptionPane.showMessageDialog(invoice_Table, "Items loaded successfully", "Load Data", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(invoice_Table, "This invoice empty", "Load Data", JOptionPane.INFORMATION_MESSAGE);
                            }
                    } catch (Exception ex) {
                        System.out.println("This item deleted");
                    }
                }
            });


            invoice_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            invoice_Table_ScrollPane.setViewportView(invoice_Table);
            invoice_Items_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            invoice_Items_Table_ScrollPane.setViewportView(invoice_Items_Table);
        }
        private void initialize_Text_Field () {
            customer_Name_TF = new JTextField();
            invoice_Data_TF = new JTextField();
            customer_Name_TF.getAccessibleContext().setAccessibleName(customer_Name_TF_Access_name);
            invoice_Data_TF.getAccessibleContext().setAccessibleName(invoice_Number_Access_name);
            customer_Name_TF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    customer_Name_Tf_Action();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    customer_Name_Tf_Action();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    customer_Name_Tf_Action();
                }
            });

            invoice_Data_TF.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    invoice_Data_TF_Action();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    invoice_Data_TF_Action();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    invoice_Data_TF_Action();
                }
            });

        }
        private void initialize_Menu_Bar () {
            menuBar = new JMenuBar();
            file_Menu = new JMenu("File");
            save_Menu_Item = new JMenuItem("Save", 'S');
            load_Menu_Item = new JMenuItem("Load", 'L');

            save_Menu_Item.setAccelerator(KeyStroke.getKeyStroke('s', KeyEvent.ALT_DOWN_MASK));
            load_Menu_Item.setAccelerator(KeyStroke.getKeyStroke('l', KeyEvent.ALT_DOWN_MASK));

            save_Menu_Item.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveFileMenuItemActionPerformed(evt);
                }
            });
            load_Menu_Item.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    loadFileMenuItemActionPerformed(evt);
                }
            });
            file_Menu.add(load_Menu_Item);

            file_Menu.add(save_Menu_Item);
            save_Menu_Item.getAccessibleContext().setAccessibleName("Save");

            menuBar.add(file_Menu);
            setJMenuBar(menuBar);
        }


    private void organize_Layout () {
            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(invoice_Table_ScrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(invoice_Table_Label)).addGap(49, 49, 49)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(customer_Name_Label)
                                                            .addComponent(invoice_Data_Label)
                                                            .addComponent(invoice_Number_Label)
                                                            .addComponent(invoice_Total_Label))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(invoice_Data_TF)
                                                            .addComponent(customer_Name_TF)
                                                            .addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addComponent(invoice_Number_Data_Label)
                                                                            .addComponent(invoice_Total_Data_Label))
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                            .addComponent(j1)
                                                                            .addComponent(j2))
                                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                            .addComponent(invoice_Items_Table_ScrollPane, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)).addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(114, 114, 114)
                                    .addComponent(create_New_Invoice_btn)
                                    .addGap(18, 18, 18)
                                    .addComponent(delete_Invoice_btn)
                                    .addGap(230, 230, 230)
                                    .addComponent(insert_New_Item_btn)
                                    .addGap(18, 18, 18)
                                    .addComponent(delete_Item_btn)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup().addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(invoice_Number_Label)
                                            .addComponent(j2)
                                            .addComponent(invoice_Table_Label)
                                            .addComponent(invoice_Number_Data_Label))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(invoice_Data_Label)
                                                            .addComponent(invoice_Data_TF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(customer_Name_Label)
                                                            .addComponent(customer_Name_TF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(invoice_Total_Label)
                                                            .addComponent(j1)
                                                            .addComponent(invoice_Total_Data_Label)))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(invoice_Items_Table_ScrollPane, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(invoice_Table_ScrollPane, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(delete_Invoice_btn)
                                                            .addComponent(create_New_Invoice_btn)
                                                            .addComponent(insert_New_Item_btn)
                                                            .addComponent(delete_Item_btn))))
                                    .addContainerGap(109, Short.MAX_VALUE))
            );
        }

        private void invoice_Data_TF_Action (){
            invoice_dtm.setValueAt(customer_Name_TF.getText(),invoice_Selected_Row,1);
        }

        private void customer_Name_Tf_Action (){
            invoice_dtm.setValueAt(customer_Name_TF.getText(),invoice_Selected_Row,2);
        }


    }