import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginTest {

    private JLabel password1, label;
    private JTextField username;
    private JButton button;
    private JPasswordField Password;

    public LoginTest() {
        // creating a JPanel class
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Login Page");
        frame.setLocation(new Point(500, 300));
        frame.setSize(new Dimension(400, 350));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a Login label at the top center of the page
        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 24)); // You can change the font as you like
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2; // span across two columns
        constraints.insets = new Insets(10, 0, 20, 0); // Add some padding around the label
        constraints.anchor = GridBagConstraints.CENTER; // Center the label horizontally in its cell
        panel.add(lblLogin, constraints);

        // Reset constraints for other components
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;

        // Username label constructor
        constraints.gridy = 1; //changed from 0 to 1
        label = new JLabel("Username");
        panel.add(label, constraints);

        // Username TextField constructor
        constraints.gridy = 2; //changed from 1 to 2
        username = new JTextField(20);
        panel.add(username, constraints);

        // Password Label constructor
        constraints.gridy = 3; //changed from 2 to 3
        password1 = new JLabel("Password");
        panel.add(password1, constraints);

        // Password TextField
        constraints.gridy = 4; //changed from 3 to 4
        Password = new JPasswordField(20);
        panel.add(Password, constraints);

        // Button constructor
        constraints.gridy = 5; //changed from 4 to 5
        constraints.insets = new Insets(10, 0, 0, 0);  // 10 pixels of space at the top
        button = new JButton("Login");
        panel.add(button, constraints);

        //signup Label constructor
        constraints.gridy = 6; //changed from 5 to 6
        JLabel lblSignup = new JLabel("<html>Don't have an account yet? <br> <u>Sign up here</u></html>");
        constraints.insets = new Insets(5, 0, 5, 0);
        panel.add(lblSignup, constraints);

        //forgot password label constructor
        constraints.gridy = 7; //changed from 6 to 7
        JLabel lblForgotPassword = new JLabel("<html><u>Forgot your password?</u></html>");
        constraints.insets = new Insets(0, 0, 0, 0);
        panel.add(lblForgotPassword, constraints);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = username.getText();
                String pass = String.valueOf(Password.getPassword());

                if (authenticate(user, pass)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            }
        });

        lblSignup.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Signup();
                frame.dispose();
            }
        });
        frame.add(panel);
    frame.setVisible(true);
    }


    private boolean authenticate(String username, String password) {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
    
            // Prepare SQL query
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
    
            // Execute query and get results
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeConnection(conn);
        }
    
        return false;
    }

    public static void main(String[] args) {
        new LoginTest();
    }
}


    