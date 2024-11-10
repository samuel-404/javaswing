import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame implements ActionListener {

    private JTextField inputField, celsiusField, fahrenheitField, kelvinField;
    private JComboBox<String> unitComboBox;
    private JButton convertButton;

    public TemperatureConverter() {
        // Set up the frame
        setTitle("Temperature Converter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Create components
        inputField = new JTextField(15);
        unitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        convertButton = new JButton("Convert");
        celsiusField = new JTextField(15);
        fahrenheitField = new JTextField(15);
        kelvinField = new JTextField(15);

        // Set editable to false
        celsiusField.setEditable(false);
        fahrenheitField.setEditable(false);
        kelvinField.setEditable(false);

        // Set font for components
        Font font = new Font("Arial", Font.PLAIN, 20);
        inputField.setFont(font);
        unitComboBox.setFont(font);
        convertButton.setFont(font);
        celsiusField.setFont(font);
        fahrenheitField.setFont(font);
        kelvinField.setFont(font);

        // Add action listener
        convertButton.addActionListener(this);

        // Add components to the frame
        add(new JLabel("Enter Temperature:"));
        add(inputField);
        add(new JLabel("Select Unit:"));
        add(unitComboBox);
        add(convertButton);
        add(new JLabel("Converted Temperatures:"));
        add(createPanel("Celsius", celsiusField));
        add(createPanel("Fahrenheit", fahrenheitField));
        add(createPanel("Kelvin", kelvinField));

        setVisible(true);
    }

    private JPanel createPanel(String label, JTextField field) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(label + ":"));
        panel.add(field);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();

            if (selectedUnit.equals("Celsius")) {
                convertFromCelsius(inputTemp);
            } else if (selectedUnit.equals("Fahrenheit")) {
                convertFromFahrenheit(inputTemp);
            } else {
                convertFromKelvin(inputTemp);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    private void convertFromCelsius(double celsius) {
        celsiusField.setText(String.format("%.2f", celsius));
        fahrenheitField.setText(String.format("%.2f", (celsius * 9 / 5) + 32));
        kelvinField.setText(String.format("%.2f", celsius + 273.15));
    }

    private void convertFromFahrenheit(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        celsiusField.setText(String.format("%.2f", celsius));
        fahrenheitField.setText(String.format("%.2f", fahrenheit));
        kelvinField.setText(String.format("%.2f", celsius + 273.15));
    }

    private void convertFromKelvin(double kelvin) {
        double celsius = kelvin - 273.15;
        celsiusField.setText(String.format("%.2f", celsius));
        fahrenheitField.setText(String.format("%.2f", (celsius * 9 / 5) + 32));
        kelvinField.setText(String.format("%.2f", kelvin));
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}