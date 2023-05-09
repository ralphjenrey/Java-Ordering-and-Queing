package Queue_app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.*;

public class Queue_form extends JFrame {

    private int counter = 0;
    public Queue<String> queue = new PriorityQueue<>();
    public Queue_form() {

        super("Admin");
        Queue_app app = new Queue_app();
        // Create a GridBagLayout and GridBagConstraints
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the layout manager for the JFrame
        setLayout(layout);

        // Set the insets for the GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create button
        JButton enqueue = new JButton("Enqueue");
        JButton dequeue = new JButton("Dequeue");
        JLabel error = new JLabel();

        String[] labelNames = {"Customer 1:", "Customer 2:", "Customer 3:", "Customer 4:", "Customer 5:", "Customer 6:", "Enter name:"};
        JTextField[] textFields = new JTextField[labelNames.length];

        for (int i = 0; i < labelNames.length; i++) {
            JLabel label = new JLabel(labelNames[i]);
            textFields[i] = new JTextField(10);

            gbc.gridx = 0;
            gbc.gridy = i;
            add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            add(textFields[i], gbc);
        }
        // ADD ENQUEUE BUTTON
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(enqueue, gbc);
        // ADD DEQUEUE BUTTON
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(dequeue, gbc);

        // Add error text
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(error, gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Set the size of the JFrame and make it visible
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        enqueue.addActionListener(e -> {
            String name = textFields[6].getText();

            if(counter != textFields.length-1){
                queue.add(name);
                app.nowServing.setText(getNowServing());
                System.out.println(queue);
                textFields[counter].setText(name);
                counter++;
                error.setText("");
            } else {
                error.setText("Queue is full");
            }
        });

        dequeue.addActionListener(e -> {
            if (counter != 0){
                error.setText("");
                String item = queue.remove();
                app.nowServing.setText(getNowServing());
                counter--;
                System.out.println("Removed item: " + item);
                for (int t = 0; t < textFields.length; t++) {
                    textFields[t].setText("");
                }

                Iterator<String> j = queue.iterator();
                for (int i = 0; i < textFields.length ; i++) {
                    if (j.hasNext()) {
                        textFields[i].setText(j.next());
                    } else {
                        textFields[i].setText("");
                    }
                }
            }
            else{
                if (queue.isEmpty()) {
                    error.setText("Queue is empty");
                }
            }
        });
    }

    public String getNowServing(){
        if (queue.isEmpty()){
            return "Empty";
        } else {
            return queue.peek();
        }
    }
}
