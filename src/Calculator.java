import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;   // window width
    int boardHeight = 540;  // window height

    // colors for calculator
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    // buttons for calculator
    String[] buttonSymbols = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    // components
    JFrame frame = new JFrame("Calculator"); // create window
    JLabel displayLabel = new JLabel(); // create display label
    JPanel displayPanel = new JPanel(); // create panel to display calculation
    JPanel buttonsPanel = new JPanel(); // create panel for buttons

    // A+B, A-B, A*B, A/B
    String A = "0";
    String operator = null;
    String B = null;

    public Calculator() {
        // set up window
        frame.setVisible(true); // set window to be visible
        frame.setSize(boardWidth, boardHeight); // set size of window
        frame.setLocationRelativeTo(null); // center align window
        frame.setResizable(false); // fix size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set close operation
        frame.setLayout(new BorderLayout());

        // set up display label
        displayLabel.setBackground(customBlack); // make background black
        displayLabel.setForeground(Color.white); // make text white
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80)); // set up font
        displayLabel.setHorizontalAlignment(JLabel.RIGHT); // right align text
        displayLabel.setText("0"); // default text is number 0
        displayLabel.setOpaque(true); // make label opaque

        // set up display panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel); // add display label to display panel
        frame.add(displayPanel, BorderLayout.NORTH); // add display panel to top of window

        // set up button panel
        buttonsPanel.setLayout(new GridLayout(5, 4)); // panel with 5 rows and 4 columns
        buttonsPanel.setBackground(customBlack); // set background black
        frame.add(buttonsPanel); // add button panel to window

        // create calculator buttons
        // iterate through array buttonSymbols
        for (int i = 0; i < buttonSymbols.length; i++) {
            
            // create and set up button
            JButton button = new JButton(); // create a button
            String buttonSymbol = buttonSymbols[i]; // get button symbol
            button.setFont(new Font("Arial", Font.PLAIN, 30)); // set button font
            button.setText(buttonSymbol); // put symbol on button
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack)); // make button border black

            // determine and set color of button
            // buttonSymbol is a topSymbol
            if (Arrays.asList(topSymbols).contains(buttonSymbol)) {
                button.setBackground(customLightGray);  // set button color to light gray
                button.setForeground(customBlack);  // set button text to black
            } 
            // buttonSymbol is a rightSymbol
            else if (Arrays.asList(rightSymbols).contains(buttonSymbol)) {
                button.setBackground(customOrange); // set button color to orange
                button.setForeground(Color.white);  // set button text to white
            } 
            // buttonSymbol is an other symbol
            else {      
                button.setBackground(customDarkGray);   // set button color to dark gray
                button.setForeground(Color.white);  // set button text to white
            }

            buttonsPanel.add(button); // add button to button panel

            // add action listener
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();   // get button that was clicked
                    String buttonSymbol = button.getText(); // get symbol of clicked button

                    // buttonSymbol is a top symbol
                    if(Arrays.asList(topSymbols).contains(buttonSymbol)){
                        // buttonSymbol is "AC" --> clear calculator
                        if(buttonSymbol == "AC"){
                            clearAll(); // clear calculator variables
                            displayLabel.setText("0");  // set text to "0"
                        }
                        // buttonSymbol is "+/-" --> invert number
                        else if(buttonSymbol == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText()); // convert number in label to a double
                            numDisplay *= -1;   // multiply current number by -1 to invert sign
                            displayLabel.setText(removeZeroDecimal(numDisplay));  // update label with inverted number
                        }
                        // buttonSymbol is "%" --> convert number to a percentage 
                        else if(buttonSymbol == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText()); // convert number in label to a double
                            numDisplay /= 100; // divide current number by 100
                            displayLabel.setText(removeZeroDecimal(numDisplay));  // update label with percentage
                        }
                    }
                    // buttonSymbol is a right symbol
                    else if (Arrays.asList(rightSymbols).contains(buttonSymbol)){

                    }
                    // buttonSymbol is an other symbol
                    else{
                        
                    }
                }
            });
        }
    }

    // clears the calculator
    public void clearAll(){
        // reset all variables
        A = "0";
        operator = null;
        B = null;
    }

    // determine whether numDisplay is a decimal number
    public String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0) return Integer.toString((int)numDisplay);  // numDisplay is a whole number --> remove decimal
        else return Double.toString(numDisplay);    // numDisplay is a decimal --> keep decimal 
    }
}