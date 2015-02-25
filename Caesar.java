import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Caesar extends JFrame {
    public Caesar() {
        super("Caesar Cipher");

        final JPanel corePanel = new JPanel(new BorderLayout());

        final JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 16));

        final JTextArea inputField = new JTextArea();
        final JTextArea outputField = new JTextArea();

        centerPanel.add(inputField);
        centerPanel.add(outputField);

        corePanel.add(centerPanel, BorderLayout.CENTER);

        final JPanel northPanel = new JPanel();

        final JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        northPanel.add(errorLabel);

        corePanel.add(northPanel, BorderLayout.NORTH);

        final JPanel southPanel = new JPanel();

        final JButton encryptButton = new JButton("Encrypt");
        final JButton decryptButton = new JButton("Decrypt");
        final JTextField shiftField = new JTextField(5);
        shiftField.setText("1");

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    errorLabel.setText("");
                    int shift = Integer.parseInt(shiftField.getText());
                    outputField.setText(encrypt(inputField.getText(), shift));
                } catch (NumberFormatException e) {
                    errorLabel.setText("Invalid shift factor");
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    errorLabel.setText("");
                    int shift = Integer.parseInt(shiftField.getText());
                    inputField.setText(decrypt(outputField.getText(), shift));
                } catch (NumberFormatException e) {
                    errorLabel.setText("Invalid shift factor");
                }
            }
        });

        southPanel.add(Box.createVerticalStrut(40));
        southPanel.add(encryptButton);
        southPanel.add(decryptButton);
        southPanel.add(new JLabel("Shift Factor"));
        southPanel.add(shiftField);

        corePanel.add(southPanel, BorderLayout.SOUTH);

        corePanel.add(Box.createHorizontalStrut(16), BorderLayout.EAST);
        corePanel.add(Box.createHorizontalStrut(16), BorderLayout.WEST);

        this.add(corePanel);

        this.pack();
        this.setSize(new Dimension(420, 400));
        this.setMinimumSize(new Dimension(380, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }

    public char shiftChar(char in, char floor, int shift) {
        if (in >= floor && in < floor + 26) {
            in = (char) (floor + (in - floor + shift) % 26);
            if (in < floor) {
                in += 26;
            }
        }
        return in;
    }

    public String encrypt(String input, int shift) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            c = shiftChar(c, 'a', shift);
            c = shiftChar(c, 'A', shift);
            output += c;
        }
        return output;
    }

    public String decrypt(String input, int shift) {
        return this.encrypt(input, -shift);
    }

    public static void main(String[] args) {
        new Caesar();
    }
}
