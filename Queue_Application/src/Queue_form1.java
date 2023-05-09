import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;
import java.util.Queue;

public class Queue_form1 extends  JFrame{
    private JPanel form1_container;
    private JTextField queue1,queue2,queue3,queue4,queue5,queue6,queue7,queue8,queue9,queue10;
    private JTextField[] queues = {queue1, queue2, queue3, queue4, queue5, queue6, queue7, queue8, queue9, queue10};
    private JButton enqueue;
    private JLabel error, nowServing;
    private JTextField name_field;
    private int counter = 0;
    public Queue<String> queue = new PriorityQueue<>();
     public Queue_form1() {
         setContentPane(form1_container);
        setTitle("Admin Interface");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);

         enqueue.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String name = name_field.getText();
                 if(counter != queues.length-1){
                     queue.add(name);
                     nowServing.setText(queue.peek());
                     System.out.println(queue.peek());
                     queues[counter].setText(name);
                     counter++;
                     error.setText("");
                 }
                 else {
                     error.setText("Queue is full");
                 }
             }
         });
     }
    public static void main(String[] args) {

        Queue_form1 form1 = new Queue_form1();
    }

}
