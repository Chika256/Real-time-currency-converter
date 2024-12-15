import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {

    // Declare UI components
    private JTextField amountField;
    private JTextField resultField;
    private JComboBox<String> currencyComboBoxFrom;
    private JComboBox<String> currencyComboBoxTo;
    private JButton convertButton;

    // Constructor to set up the UI
    public CurrencyConverter() {
        // Set up the frame
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Create labels and fields
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField(10);
        panel.add(amountField);

        panel.add(new JLabel("From Currency:"));
        currencyComboBoxFrom = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        panel.add(currencyComboBoxFrom);

        panel.add(new JLabel("To Currency:"));
        currencyComboBoxTo = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR"});
        panel.add(currencyComboBoxTo);

        panel.add(new JLabel("Converted Amount:"));
        resultField = new JTextField(10);
        resultField.setEditable(false);
        panel.add(resultField);

        convertButton = new JButton("Convert");
        panel.add(convertButton);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        // Action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    // Method to perform the currency conversion
    private void convertCurrency() {
        try {
            // Get the amount from the text field
            double amount = Double.parseDouble(amountField.getText());

            // Get the selected currencies
            String fromCurrency = (String) currencyComboBoxFrom.getSelectedItem();
            String toCurrency = (String) currencyComboBoxTo.getSelectedItem();

            // Perform the conversion (example using a simple conversion rate, you can extend it with real exchange rates)
            double conversionRate = getConversionRate(fromCurrency, toCurrency);
            double convertedAmount = amount * conversionRate;

            // Display the result
            resultField.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the amount.");
        }
    }

    // Method to get a conversion rate (this is just an example, you can replace it with real API calls)
    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Example static rates (this is for illustration, you can replace it with real API or rates)
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) return 0.92;
        if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) return 0.75;
        if (fromCurrency.equals("USD") && toCurrency.equals("INR")) return 83.00;
        if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) return 1.09;
        if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) return 1.33;
        if (fromCurrency.equals("INR") && toCurrency.equals("USD")) return 0.012;
        return 1.0;  // If currencies are the same
    }

    public static void main(String[] args) {
        // Run the UI on the Swing event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}
