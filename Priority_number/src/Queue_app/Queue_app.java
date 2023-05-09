package Queue_app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.*;

public class Queue_app extends JFrame{

        public JLabel nowServing = new JLabel("Hello");
        public Queue_app(){
                super("Customer");
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints gbc = new GridBagConstraints();
                setLayout(layout);

                JLabel subtitle = new JLabel("Now Serving");

                //position of nowServing
                gbc.gridx = 0; gbc.gridy = 2;
                add(nowServing, gbc);

                gbc.gridx = 0; gbc.gridy = 0;
                add(subtitle, gbc);


                // Set the size of the JFrame and make it visible
                setSize(300, 400);
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;





        }

        public static void main(String[] args) {
                Queue_form form = new Queue_form();
        }
        }


