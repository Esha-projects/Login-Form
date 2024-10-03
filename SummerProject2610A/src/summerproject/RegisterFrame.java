package summerproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterFrame {
	private JFrame rFrame;
    private JTextField fNField;
    private JTextField lNField;
    private JTextField eField;
    private JPasswordField pField;
    private JButton rButton;

    public RegisterFrame() {
        rFrame = new JFrame("Register");
        rFrame.setSize(300, 200);
        rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fNField = new JTextField(20);
        lNField = new JTextField(20);
        eField = new JTextField(20);
        pField = new JPasswordField(20);
        rButton = new JButton("Register");

        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = fNField.getText();
                String lastName = lNField.getText();
                String email = eField.getText();
                String password = new String(pField.getPassword());

                if (isEmailRegistered(email)) {
                    JOptionPane.showMessageDialog(rFrame, "Duplicate Email Address Found!",
                            "Registration Failed", JOptionPane.ERROR_MESSAGE);
                } else {
                	//Updating data file with updated values
                    try (PrintWriter writer = new PrintWriter(new FileWriter("users.csv", true))) {
                        writer.println(firstName + "," + lastName + "," + email + "," + password);
                        writer.close();
                        JOptionPane.showMessageDialog(rFrame, "Registration is successful!",
                                "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                        rFrame.dispose();
                        new LoginFrame();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("First Name:"));
        panel.add(fNField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lNField);
        panel.add(new JLabel("Email:"));
        panel.add(eField);
        panel.add(new JLabel("Password:"));
        panel.add(pField);
        panel.add(rButton);

        rFrame.add(panel);
        rFrame.setVisible(true);

}
    
    private boolean isEmailRegistered(String email) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] items = input.split(",");
                if (items[2].equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}