import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ordering_UI extends JFrame{
    private JPanel ordering_ui;
    private JButton add1,add2,add3,add4,add5,add6,add7,add8,add9,add10;
    private JButton sub1,sub2,sub3,sub4,sub5,sub6,sub7,sub8,sub9,sub10;
    private JTextField quantity1,quantity2,quantity3,quantity4,quantity5,quantity6,quantity7,quantity8,quantity9,quantity10;
    private int[] quantity = new int[10];
    private JTable table1;
    private JButton printButton;
    private JTable table2;
    private JTextField textField1;
    private JLabel food1,food2,food3,food4,food5,food6,food7,food8,food9,food10;
    private JLabel food1Price,food2Price,food3Price,food4Price,food5Price,food6Price,food7Price,food8Price,food9Price,food10Price;
    private JLabel[] foodPrice = {food1Price, food2Price, food3Price, food4Price, food5Price,
            food6Price, food7Price, food8Price, food9Price, food10Price};
    private double[] foodPrices = new double[foodPrice.length-1];
    private int total = 0;





    public Ordering_UI(){
        //Main Panel
        setContentPane(ordering_ui);
        setSize(getPreferredSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);



        //Table
//        TableModel dataModel = new
//                AbstractTableModel() {
//                    public int getColumnCount() {
//                        return 0;
//                    }
//
//                    public int getRowCount() {
//                        return 0;
//                    }
//
//                    public Object getValueAt(int row, int col) {
//
//                    }
//                };
//        table1.setModel(dataModel);


        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[0] += 1;
                quantity1.setText(Integer.toString(quantity[0]));
                total = (int) (foodPrices[0] * quantity[0]);
                System.out.println(total);
            }
        });
        sub1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[0] <= 0){
                    return;
                }
                quantity[0] -= 1;
                quantity1.setText(Integer.toString(quantity[0]));
                total = (int) (foodPrices[0] * quantity[0]);
                System.out.println(total);
            }
        });

        add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[1] += 1;
                quantity2.setText(Integer.toString(quantity[1]));
               // quantity2.setText(Integer.toString(quantity[1]));
            }
        });
        sub2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[1] <= 0){
                    return;
                }
                quantity[1] -= 1;
                quantity2.setText(Integer.toString(quantity[1]));
            }
        });

        add3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[2] += 1;
                quantity3.setText(Integer.toString(quantity[2]));
            }
        });
        sub3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[2] <= 0){
                    return;
                }
                quantity[2] -= 1;
                quantity3.setText(Integer.toString(quantity[2]));
            }
        });
        add4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[3] += 1;
                quantity4.setText(Integer.toString(quantity[3]));
            }
        });
        sub4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[3] <= 0){
                    return;
                }
                quantity[3] -= 1;
                quantity4.setText(Integer.toString(quantity[3]));
            }
        });
        add5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[4] += 1;
                quantity5.setText(Integer.toString(quantity[4]));
            }
        });
        sub5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[4] <= 0){
                    return;
                }
                quantity[4] -= 1;
                quantity5.setText(Integer.toString(quantity[4]));
            }
        });
        add6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[5] += 1;
                quantity6.setText(Integer.toString(quantity[5]));
            }
        });
        sub6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[5] <= 0){
                    return;
                }
                quantity[5] -= 1;
                quantity6.setText(Integer.toString(quantity[5]));
            }
        });
        add7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[6] += 1;
                quantity7.setText(Integer.toString(quantity[6]));
            }
        });
        sub7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[6] <= 0){
                    return;
                }
                quantity[6] -= 1;
                quantity7.setText(Integer.toString(quantity[6]));
            }
        });
        add8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[7] += 1;
                quantity8.setText(Integer.toString(quantity[7]));
            }
        });
        sub8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[7] <= 0){
                    return;
                }
                quantity[7] -= 1;
                quantity8.setText(Integer.toString(quantity[7]));
            }
        });
        add9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[8] += 1;
                quantity9.setText(Integer.toString(quantity[8]));
            }
        });
        sub9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[8] <= 0){
                    return;
                }
                quantity[8] -= 1;
                quantity9.setText(Integer.toString(quantity[8]));
            }
        });
        add10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity[9] += 1;
                quantity10.setText(Integer.toString(quantity[9]));
            }
        });
        sub10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity[9] <= 0){
                    return;
                }
                quantity[9] -= 1;
                quantity10.setText(Integer.toString(quantity[9]));
            }
        });

        for (int i = 0; i < foodPrice.length; i++) {
            String price = "";
            price = foodPrice[i].getText();
            price = price.replaceAll("[^0-9.]", "");
            foodPrices[i] = Double.parseDouble(price);
        }


    }

    public static void main(String[] args) {
        Ordering_UI form2 = new Ordering_UI();
    }
}
