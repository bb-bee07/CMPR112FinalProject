/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cmpr112;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CMPR112 {
    public static void main(String[] args) {
        boolean validYarnWeight = false;

        while (!validYarnWeight) {
            // Get yarn weight from user
            String yarnInput = JOptionPane.showInputDialog("Enter yarn weight (0-7):");

            try {
                // Convert yarn input to an integer
                int yarnWeight = Integer.parseInt(yarnInput);

                // Check if the yarn weight is within the valid range (0-7)
                if (yarnWeight >= 0 && yarnWeight <= 7) {
                    validYarnWeight = true; // Set flag to exit the loop
                    // Get crochet hook size from user
                    String hookInput = JOptionPane.showInputDialog("Enter crochet hook size (0.6-30 mm):");

                    try {
                        // Convert hook input to a double
                        double hookSize = Double.parseDouble(hookInput);

                        // Get user preference for free or paid patterns
                        boolean isFree = getFreePatternPreference();

                        // Generate the URL based on yarn weight, hook size, and pattern availability
                        String url = "https://www.ravelry.com/patterns/search#craft=crochet&weight="
                                + getYarnWeightName(yarnWeight) + "&hooks=" + hookSize + "mm";

                        if (isFree) {
                            url += "&availability=free";
                        }

                        url += "&sort=popularity&view=captioned_thumbs";

                        // Open the web browser to navigate to the URL
                        openWebPage(url);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number"
                                + " for crochet hook size.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Yarn Weight! Yarn weight must be a "
                            + "number between 0 and 7.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number for"
                        + " yarn weight.");
            }
        }
    }

    // Helper method to convert yarn weight numeric value to its colloquial name
    private static String getYarnWeightName(int yarnWeight) {
        switch (yarnWeight) {
            case 0: return "thread&cobweb&lace";
            case 1: return "light-fingering&fingering";
            case 2: return "sport";
            case 3: return "dk";
            case 4: return "worsted&aran";
            case 5: return "bulky";
            case 6: return "super-bulky";
            case 7: return "jumbo";
            default: return "";
        }
    }

    // Helper method to get user preference for free or paid patterns
    private static boolean getFreePatternPreference() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Do you want to search for only free patterns?", 
                "Pattern Preference", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    // Helper method to open a web page in the default browser
    private static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
