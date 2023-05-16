import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class Ordering_UI extends JFrame{
    private JPanel ordering_ui;
    private JButton add1,add2,add3,add4,add5,add6,add7,add8,add9,add10;
    private JButton sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10;
    private JTextField quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    private int[] quantity = new int[10];
    private JTable table1;
    private JButton printButton;
    private JTextField customerName;
    private JLabel food1,food2,food3,food4,food5,food6,food7,food8,food9,food10;
    // create a new String array and assign all JLabels to it
    private JLabel[] foodLabels = {food1,food2,food3,food4,food5,food6,food7,food8,food9,food10};

    private JLabel food1Price,food2Price,food3Price,food4Price,food5Price,food6Price,food7Price,food8Price,food9Price,food10Price;
    private JTable table2;
    private JLabel totalPrice;
    private JLabel[] foodPrice = {food1Price, food2Price, food3Price, food4Price, food5Price,
            food6Price, food7Price, food8Price, food9Price, food10Price};
    private double[] foodPrices = new double[foodPrice.length];
    private double[] subTotal = new double[foodPrice.length];








    public Ordering_UI(){
        //Main Panel
        setContentPane(ordering_ui);
        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        int[] priorityNumber = {1};
        createTable();
        //Assign all foodPrice Label to a String of Array
        for (int i = 0; i < foodPrice.length; i++) {
            foodPrices[i] = Double.parseDouble(foodPrice[i].getText().substring(1));
            System.out.println(foodPrices[i]);
        }

        setActionListenersForFoodItem(0, add1, sub1, quantity1);
        setActionListenersForFoodItem(1, add2, sub2, quantity2);
        setActionListenersForFoodItem(2, add3, sub3, quantity3);
        setActionListenersForFoodItem(3, add4, sub4, quantity4);
        setActionListenersForFoodItem(4, add5, sub5, quantity5);
        setActionListenersForFoodItem(5, add6, sub6, quantity6);
        setActionListenersForFoodItem(6, add7, sub7, quantity7);
        setActionListenersForFoodItem(7, add8, sub8, quantity8);
        setActionListenersForFoodItem(8, add9, sub9, quantity9);
        setActionListenersForFoodItem(9, add10, sub10, quantity10);

        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = customerName.getText();

                DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                DefaultTableModel model2 = (DefaultTableModel) table2.getModel();


                // Check if cart is empty
                if (model1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Cart cannot be empty");
                    return;
                }

                //Reference the JTextField quantity to clear Quantity and clear all values
                JTextField[] clearQuantity = {quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10};
                for (int i = 0; i < quantity.length; i++) {
                    clearQuantity[i].setText("0");
                    quantity[i] = 0;
                }

                // Add the row to model2 with the priority number
                model2.addRow(new Object[]{priorityNumber[0], name, totalPrice.getText().substring(13)});
                priorityNumber[0]++;

                // Clear table1
                model1.setRowCount(0);
                totalPrice.setText("Total Price: ");
            }

        });


    }

    private void setActionListenersForFoodItem(int index, JButton addButton, JButton subButton, JTextField quantityField) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[index] += 1;
                quantityField.setText(Integer.toString(quantity[index]));
                double total = (foodPrices[index] * quantity[index]);
                subTotal[index] = total;
                updateTotalPrice(subTotal);
                updateTable(index, quantity[index], total);
            }
        });
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[index] <= 0) {
                    return;
                }
                quantity[index] -= 1;
                quantityField.setText(Integer.toString(quantity[index]));
                double total = (foodPrices[index] * quantity[index]);
                subTotal[index] = total;
                updateTotalPrice(subTotal);
                updateTable(index, quantity[index], total);
            }
        });
    }


    public void createTable(){
        table1.setModel(new DefaultTableModel(
                null,
                new String[]{"Food","Quantity","Price","Subtotal"}
        ));
        table2.setModel(new DefaultTableModel(
                null, new String[]{"Priority Number","Customer Name","Total Order"}
        ));

    }

    public void updateTable(int foodIndex, int newQuantity, double newTotal) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(foodLabels[foodIndex].getText())) {
                if (newQuantity == 0) {
                    model.removeRow(i); // Remove the row if newQuantity is 0
                    return;
                }
                model.setValueAt(newQuantity, i, 1);
                model.setValueAt(foodPrice[foodIndex].getText(), i, 2);
                model.setValueAt(newTotal, i, 3);
                return;
            }
        }
        // If row for the food item is not found, add a new row
        model.addRow(new Object[]{foodLabels[foodIndex].getText(), newQuantity, foodPrice[foodIndex].getText(), newTotal});
    }
    public void updateTotalPrice(double[] subTotal) {
        double total = 0.0;
        for (int i = 0; i < subTotal.length; i++) {
            total+=subTotal[i];
        }
        totalPrice.setText("Total Price: ₱" + total);
    }

    public static void main(String[] args) {
        Queue_form1 form1 = new Queue_form1(name);
    }

}
