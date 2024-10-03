package summerproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class MainFrame {
	
	private JFrame mFrame;
    private JButton lButton;
    private JButton rButton;

    public MainFrame() {
        mFrame = new JFrame("User System");
        mFrame.setSize(550, 350);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lButton = new JButton("Login");
        rButton = new JButton("Register");

        
        lButton.addActionListener(new ActionListener() {
            @Override //actionPerformed method is overriding the method from the ActionListener interface.
            public void actionPerformed(ActionEvent e) {
                mFrame.dispose();
                new LoginFrame();
            }
        });

        rButton.addActionListener(new ActionListener() {
            @Override //actionPerformed method is overriding the method from the ActionListener interface.
            public void actionPerformed(ActionEvent e) {
                mFrame.dispose();
                new RegisterFrame();
            }
        });

        JPanel panel = new JPanel();
        panel.add(lButton);
        panel.add(rButton);

        mFrame.add(panel);
        mFrame.setVisible(true);

        // Creates users.CSV file if it is not present in the program directory
        File file = new File("users.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create a new instance of mFrame Class
		new MainFrame();
	}
}
