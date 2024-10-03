package summerproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame {
	private JFrame lFrame;
    private JTextField eField;
    private JPasswordField pField;
    private JButton lButton;

    public LoginFrame() {
        lFrame = new JFrame("Login");
        lFrame.setSize(300, 250);
        lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eField = new JTextField(20);
        pField = new JPasswordField(20);
        lButton = new JButton("Login");

        lButton.addActionListener(new ActionListener() {
            @Override //actionPerformed method is overriding the method from the ActionListener interface.
            public void actionPerformed(ActionEvent e) {
                String email = eField.getText();
                String password = new String(pField.getPassword());

                if (isValidCredentials(email, password)) {
                    JOptionPane.showMessageDialog(lFrame, "Login is Successful!",
                            "Login Success", JOptionPane.INFORMATION_MESSAGE);
                    lFrame.dispose();
                    new ProfileFrame(email);
                } else {
                    JOptionPane.showMessageDialog(lFrame, "Invalid email or password is entered!",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Email:"));
        panel.add(eField);
        panel.add(new JLabel("Password:"));
        panel.add(pField);
        panel.add(lButton);

        lFrame.add(panel);
        lFrame.setVisible(true);
    }

    private boolean isValidCredentials(String email, String password) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] items = input.split(",");
                if (items[2].equals(email) && items[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
