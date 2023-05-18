import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class QueueForm1 extends JFrame {
    private JPanel form1_container;
    private JTextField queue1, queue2, queue3, queue4, queue5, queue6, queue7, queue8, queue9, queue10;
    private JTextField[] queues = {queue1, queue2, queue3, queue4, queue5, queue6, queue7, queue8, queue9, queue10};
    private JButton dequeue;
    private JLabel nowServing;
    private JLabel error;
    private JTextField name_field;
    private OrderingUI form;

    public QueueForm1(Queue<String> customerQueue) {
        setContentPane(form1_container);
        setTitle("Admin Interface");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);
        setQueueValues(customerQueue);
        dequeue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customerQueue.isEmpty()) {
                    customerQueue.remove();
                    nowServing.setText(customerQueue.peek());
                    setQueueValues(customerQueue);
                    error.setText("");
                    DefaultTableModel model2 = form.getModel2();
                    // Check if the model2 has rows
                    if (model2.getRowCount() > 0) {
                        // Remove the first row
                        model2.removeRow(0);
                    }
                } else {
                    error.setText("Queue is empty");
                }
            }
        });
    }

    public void setQueueValues(Queue<String> customerQueue) {
        // Create a temporary queue to store the remapped values
        Queue<String> tempQueue = new LinkedList<>(customerQueue);

        // Update the values in the queues array
        for (int i = 0; i < queues.length; i++) {
            if (!tempQueue.isEmpty()) {
                queues[i].setText(tempQueue.remove());
            } else {
                queues[i].setText("");
            }
        }
    }



    public static void main(String[] args) {
        // Assuming you want to test the Queue_form1 class
        Queue<String> customerQueue = new LinkedList<>();
        QueueForm1 form1 = new QueueForm1(customerQueue);
    }
}
