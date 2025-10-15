import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BasicCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String num1 = "", num2 = "", operator = "";

    public BasicCalculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.addActionListener(this);
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (operator.equals("")) {
                num1 += command;
            } else {
                num2 += command;
            }
            textField.setText(num1 + operator + num2);
        } else if (command.equals("C")) {
            num1 = num2 = operator = "";
            textField.setText("");
        } else if (command.equals("=")) {
            double n1 = num1.isEmpty() ? 0 : Double.parseDouble(num1);
            double n2 = num2.isEmpty() ? 0 : Double.parseDouble(num2);
            double result = 0;
            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/": result = n2 != 0 ? n1 / n2 : 0; break;
            }
            textField.setText(String.valueOf(result));
            num1 = String.valueOf(result); num2 = ""; operator = "";
        } else {
            if (!num1.isEmpty() && operator.equals("")) {
                operator = command;
                textField.setText(num1 + operator);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicCalculator().setVisible(true));
    }
}
