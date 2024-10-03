package summerproject;

//Allows users to edit their profile information.

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditProfileFrame {
  private JFrame ePFrame;
  private JTextField fNField;
  private JTextField lNField;
  private JButton sButton;
  //private String email;

  public EditProfileFrame(String email) {
	  //this.email = email;
	  
      ePFrame = new JFrame("Edit Profile");
      ePFrame.setSize(300, 150);
      ePFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      fNField = new JTextField(20);
      lNField = new JTextField(20);
      JLabel emailLabel = new JLabel("Email:");
      JTextField emailField = new JTextField(email);
      emailField.setEditable(false);
      sButton = new JButton("Save");

      sButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String firstName = fNField.getText();
              String lastName = lNField.getText();

              // Update users.csv with new first name and last name
              try {
                  File inputFile = new File("users.csv");
                  File tempFile = new File("temp.csv");

                  BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                  BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                  String input;
                  while ((input = reader.readLine()) != null) {
                      String[] items = input.split(",");
                      if (items[2].equals(email)) {
                          // Update names
                    	  items[0] = firstName;
                    	  items[1] = lastName;
                          input = String.join(",", items);
                      }
                      writer.write(input + System.getProperty("line.separator"));
                  }

                  reader.close();
                  writer.close();

                  if (!inputFile.delete()) {
                      throw new IOException("Original file deletion failed");
                  }
                  if (!tempFile.renameTo(inputFile)) {
                      throw new IOException("Original file rename failed");
                  }

                  JOptionPane.showMessageDialog(ePFrame, "User Profile is updated successfully!",
                          "Profile Update Success", JOptionPane.INFORMATION_MESSAGE);
                  ePFrame.dispose();
                  new ProfileFrame(email);
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
          }
      });

      JPanel panel = new JPanel();
      panel.add(new JLabel("First Name:"));
      panel.add(fNField);
      panel.add(new JLabel("Last Name:"));
      panel.add(lNField);
      panel.add(emailLabel);
      panel.add(emailField);
      panel.add(sButton);

      ePFrame.add(panel);
      ePFrame.setVisible(true);

      // Load current first name and last name from users.csv
      loadProfile(email);
  }
  
  private void loadProfile(String email) {
      String[] profileData = ProfileLoad.loadProfile(email);
      fNField.setText(profileData[0]);
      lNField.setText(profileData[1]);
  }
}

