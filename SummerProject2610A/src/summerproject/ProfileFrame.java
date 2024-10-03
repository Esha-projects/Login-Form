package summerproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfileFrame {
    private JFrame pFrame;
    private  JLabel fNLabel;
    private  JLabel lNLabel;
    private JLabel eLabel;


    public ProfileFrame(String email) {

        pFrame = new JFrame("Profile");
        pFrame.setSize(300, 200);
        pFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fNLabel = new JLabel();
        lNLabel = new JLabel();
        eLabel = new JLabel(email); 

        loadProfile(email);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("First Name:"));
        panel.add(fNLabel);
        panel.add(new JLabel("Last Name:"));
        panel.add(lNLabel);
        panel.add(new JLabel("Email:"));
        panel.add(eLabel);

        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pFrame.dispose();
                new EditProfileFrame(email);
            }
        });

        panel.add(editProfileButton);
        pFrame.add(panel);
        pFrame.setVisible(true);
    }
    
    private void loadProfile(String email) {
        String[] profileData = ProfileLoad.loadProfile(email);
        fNLabel.setText(profileData[0]);
        lNLabel.setText(profileData[1]);
    }
}


