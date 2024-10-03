package summerproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfileLoad {

    public static String[] loadProfile(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] items = input.split(",");
                if (items[2].equals(email)) {
                    return new String[] { items[0], items[1] }; // Retrieves First and Last name
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[] { "", "" }; // Returns nothing if not found
    }
}
