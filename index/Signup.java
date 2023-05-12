import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Signup {

    private JFrame frame;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnSubmit;

    public Signup() {
        // Setup JFrame
        frame = new JFrame("Signup");
        frame.setSize(400, 350); // Set specific size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use GridBagLayout for responsive layout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);

        // Add a Signup label at the top center of the page
        JLabel lblSignup = new JLabel("SIGNUP");
        lblSignup.setFont(new Font("Arial", Font.BOLD, 24)); // You can change the font as you like
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // span across two columns
        gbc.insets = new Insets(10, 0, 20, 0); // Add some padding around the label
        gbc.anchor = GridBagConstraints.CENTER; // Center the label horizontally in its cell
        frame.add(lblSignup, gbc);

        // Username Label
        JLabel lblUsername = new JLabel("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Back to default value
        gbc.insets = new Insets(0, 0, 5, 0); // Add some padding below the label
        gbc.anchor = GridBagConstraints.WEST; // Align the label to the left (west) in its cell
        frame.add(lblUsername, gbc);

        // Username TextField
        tfUsername = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(tfUsername, gbc);

        // Password Label
        JLabel lblPassword = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(lblPassword, gbc);

        // Password TextField
        pfPassword = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(pfPassword, gbc);

        // Submit Button
        btnSubmit = new JButton("Create");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // span across two columns
        gbc.insets = new Insets(10, 0, 0, 0); // Add some padding above the button
        frame.add(btnSubmit, gbc);

        // Login Label constructor
        JLabel lblLogin = new JLabel("<html>I already have an account. <br><u>Login here</u></html>");
        gbc.gridx = 0;
        gbc.gridy = 6; // increment the y position
        gbc.insets = new Insets(10, 0, 0, 0); // Add some padding above the label
        frame.add(lblLogin, gbc);

        // Setup action listener for the create button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(pfPassword.getPassword());

                if (createAccount(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Account created successfully!");
                    frame.dispose(); // Close the signup frame
                    new LoginTest(); // Open the login frame
                } else {
                    JOptionPane.showMessageDialog(frame, "Unable to create account. Please try again.");
                }
            }
        });

        // Add mouse listener to the label
        lblLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginTest(); // Open the Login frame
                frame.dispose(); // Close the Signup frame
            }
        });

        
        // frame.pack(); // Comment out this line to keep specific size
        frame.setLocationRelativeTo(null); // center the frame
        frame.setVisible(true);
    }

    private boolean createAccount(String username, String password) {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            // Prepare SQL query
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute query and get results
            int result = pstmt.executeUpdate();
            return result > 0; // return true if at least one record is affected
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }

        return false;
    }

    public static void main(String[] args) {
        new Signup();
    }
}