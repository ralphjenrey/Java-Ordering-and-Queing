import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderingUI extends JFrame {
    private JPanel orderingUI;
    private JButton add1,add2,add3,add4,add5,add6,add7,add8,add9,add10;
    private JButton sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10;
    private JButton[] addButtonArray;
    private JButton[] subButtonArray;
    private JLabel food1,food2,food3,food4,food5,food6,food7,food8,food9,food10;
    private JLabel food1Price,food2Price,food3Price,food4Price,food5Price,food6Price,food7Price,food8Price,food9Price,food10Price;
    private JTextField quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    private JTextField[] quantityFieldArray;
    private List<Integer> quantities;
    private JTable table1;
    private JButton printButton;
    private JTextField customerName;
    private JLabel[] foodLabels;
    private JLabel[] foodPriceLabels;
    private JTable table2;
    private JLabel totalPrice;
    private double[] foodPrices;
    private double[] subTotal;
    private Queue<String> customerQueue;
    private QueueForm1 form1;

    public OrderingUI() {
        initComponents();
        initListeners();
        initFoodPrices();
        customerQueue = new LinkedList<>();
        form1 = new QueueForm1(customerQueue, this);
    }


    private void initComponents() {
        setContentPane(orderingUI);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        // Get the screen size
        Dimension screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // Calculate the maximum x and y coordinates
        int X = 0; //left vertical
        int Y = (screenHeight - getHeight())/2; //middle horizontal
        setLocation(X,Y);
        setVisible(true);

        addButtonArray = new JButton[]{add1, add2, add3, add4, add5, add6, add7, add8, add9, add10};
        subButtonArray = new JButton[]{sub1, sub2, sub3, sub4, sub5, sub6, sub7, sub8, sub9, sub10};
        quantityFieldArray = new JTextField[]{quantity1, quantity2, quantity3, quantity4, quantity5,
                quantity6, quantity7, quantity8, quantity9, quantity10};
        foodLabels = new JLabel[]{food1, food2, food3, food4, food5, food6, food7, food8, food9, food10};
        foodPriceLabels = new JLabel[]{food1Price, food2Price, food3Price, food4Price, food5Price,
                food6Price, food7Price, food8Price, food9Price, food10Price};

        quantities = new ArrayList<>();
        for (int i = 0; i < quantityFieldArray.length; i++) {
            quantities.add(0);
        }

        DefaultTableModel model1 = new DefaultTableModel(
                null,
                new String[]{"Food", "Quantity", "Price", "Subtotal"}
        );
        table1.setModel(model1);

        DefaultTableModel model2 = new DefaultTableModel(
                null, new String[]{"Priority Number", "Customer Name", "Total Order"}
        );
        table2.setModel(model2);
    }

    private void initListeners() {
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (customerName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill customer name");
                } else {
                    DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                    DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

                    if (model1.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Cart cannot be empty");
                        return;
                    }

                    customerQueue.add(customerName.getText());
                    String name = customerName.getText();
                    form1.setQueueValues(customerQueue);

                    double total = calculateTotalPrice(model1);

                    JTextField[] clearQuantity = quantityFieldArray;
                    clearQuantities();

                    model2.addRow(new Object[]{model2.getRowCount() + 1, name, "₱" + total});
                    clearSubtotals();

                    model1.setRowCount(0);
                    totalPrice.setText("Total Price: ");
                }
            }
        });

        for (int i = 0; i < addButtonArray.length; i++) {
            final int index = i;
            addButtonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int quantity = quantities.get(index) + 1;
                    quantities.set(index, quantity);
                    quantityFieldArray[index].setText(Integer.toString(quantity));
                    double total = foodPrices[index] * quantity;
                    subTotal[index] = total;
                    updateTotalPrice();
                    updateTable(index, quantity, total);
                }
            });

            subButtonArray[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int quantity = quantities.get(index);
                    if (quantity > 0) {
                        quantity--;
                        quantities.set(index, quantity);
                        quantityFieldArray[index].setText(Integer.toString(quantity));
                        double total = foodPrices[index] * quantity;
                        subTotal[index] = total;
                        updateTotalPrice();
                        updateTable(index, quantity, total);
                    }
                }
            });

            quantityFieldArray[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateQuantity(index);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateQuantity(index);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateQuantity(index);
                }
            });
        }
    }

    private void updateQuantity(int index) {
        JTextField quantityField = quantityFieldArray[index];
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            quantities.set(index, quantity);
            double total = foodPrices[index] * quantity;
            subTotal[index] = total;
            updateTotalPrice();
            updateTable(index, quantity, total);
        } catch (NumberFormatException ex) {
            // Handle invalid input if needed
        }
        if (quantityField.getText().trim().isEmpty() || quantities.get(index) <= 0) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            for (int row = 0; row < model.getRowCount(); row++) {
                if (model.getValueAt(row, 0).equals(foodLabels[index].getText())) {
                    quantities.set(index, 0);
                    subTotal[index] = 0;
                    model.removeRow(row);
                    updateTotalPrice();
                    break;
                }
            }
        }
    }

    private void updateTable(int foodIndex, int newQuantity, double newTotal) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(foodLabels[foodIndex].getText())) {
                if (newQuantity == 0) {
                    model.removeRow(i);
                    return;
                }
                model.setValueAt(newQuantity, i, 1);
                model.setValueAt(foodPriceLabels[foodIndex].getText(), i, 2);
                model.setValueAt(newTotal, i, 3);
                return;
            }
        }
        if (newQuantity > 0) {
            model.addRow(new Object[]{foodLabels[foodIndex].getText(), newQuantity, foodPriceLabels[foodIndex].getText(), newTotal});
        }
    }

    private void initFoodPrices() {
        foodPrices = new double[foodPriceLabels.length];
        subTotal = new double[foodPriceLabels.length];
        for (int i = 0; i < foodPriceLabels.length; i++) {
            foodPrices[i] = Double.parseDouble(foodPriceLabels[i].getText().substring(1));
            subTotal[i] = 0;
        }
    }

    private void clearQuantities() {
        for (int i = 0; i < quantityFieldArray.length; i++) {
            quantities.set(i, 0);
            quantityFieldArray[i].setText("");
        }
    }

    private void clearSubtotals() {
        for (int i = 0; i < subTotal.length; i++) {
            subTotal[i] = 0;
        }
    }

    private double calculateTotalPrice(DefaultTableModel model) {
        double total = 0;
        for (int row = 0; row < model.getRowCount(); row++) {
            double subtotal = (double) model.getValueAt(row, 3);
            total += subtotal;
        }
        return total;
    }

    private void updateTotalPrice() {
        double total = 0;
        for (double sub : subTotal) {
            total += sub;
        }
        totalPrice.setText("Total Price: ₱" + total);
    }

    public void removeFirstRowFromTable2() {
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        if (model.getRowCount() > 0) {
            model.removeRow(0);
            updatePriorityNumbers();
        }
    }
    private void updatePriorityNumbers() {
        DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
        for (int row = 0; row < model2.getRowCount(); row++) {
            model2.setValueAt(row + 1, row, 0); // Update the priority number
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrderingUI();
            }
        });
    }
}


