import javax.swing.*;
import java.awt.*;
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
    private OrderingUI orderingUI;
    public QueueForm1(Queue<String> customerQueue, OrderingUI orderingUI) {
        setContentPane(form1_container);
        setTitle("Admin Interface");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getPreferredSize());
        // Get the screen size
        Dimension screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // Calculate the maximum x and y coordinates
        int X = screenWidth - getWidth(); //left vertical
        int Y = (screenHeight - getHeight())/2; //middle horizontal
        setLocation(X,Y);
        setVisible(true);
        setQueueValues(customerQueue);
        this.orderingUI = orderingUI;
        nowServing.setText(customerQueue.peek());
        dequeue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customerQueue.isEmpty()) {
                    customerQueue.remove();
                    nowServing.setText(customerQueue.peek());
                    setQueueValues(customerQueue);
                    error.setText("");
                } else {
                    error.setText("Queue is empty");
                }
                // Remove the first row from table2 in OrderingUI
                orderingUI.removeFirstRowFromTable2();
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
}
