

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import AtmSystem.*;


public class CheckBalanceGUI extends JPanel {
    private JPanel checkBalPanel;
    private JLabel transText;
    private JLabel accHead;
    private JButton yesButton;
    private JButton noButton;
    private JLabel receiptText;
    static JTextField amount = new JTextField("");
    String MAIN_COLOR = "#5EA880";
    String MAIN_COLOR_DARK = "#2D5862";
    String MAIN_HOVER = "#458577";
    String MAIN_TEXT = "#FFFFFF";
    String MAIN_PANEL = "#4e4d4e";
    String MAIN_BACKGROUND = "#343435";
    String FONT = "Roboto";
    

    public CheckBalanceGUI(Account acc) {
        checkBalPanel = new JPanel();
        checkBalPanel.setPreferredSize (new Dimension (400, 250));
        checkBalPanel.setLayout (null);
        checkBalPanel.setBackground(Color.decode(MAIN_PANEL));
        //construct components
        transText = new JLabel ("Your Balance:", SwingConstants.CENTER);
        transText.setForeground(Color.decode(MAIN_TEXT));
        transText.setFont(new Font(FONT, Font.PLAIN, 20));
        transText.setHorizontalAlignment(JLabel.CENTER);
        accHead = new JLabel ("Account #"+Integer.toString(acc.getAccID()), SwingConstants.CENTER);
        accHead.setFont(new Font(FONT, Font.BOLD, 30));
        accHead.setForeground(Color.decode(MAIN_TEXT));
        accHead.setHorizontalAlignment(JLabel.CENTER);
        amount.setFont(new Font(FONT, Font.PLAIN, 30));
        amount.setHorizontalAlignment(JTextField.CENTER);
        amount.setEditable(false);
        amount.setBounds(140,90,120,50);
        amount.setBackground(Color.BLACK);
        amount.setForeground(Color.decode(MAIN_COLOR));
        amount.setText("$"+acc.getBalance());
        yesButton = new JButton ("YES");
        yesButton.setBackground(Color.decode(MAIN_COLOR));
        yesButton.setForeground(Color.decode(MAIN_TEXT));
        yesButton.setFont(new Font(FONT, Font.BOLD, 20));
        noButton = new JButton ("NO");
        noButton.setBackground(Color.decode(MAIN_COLOR));
        noButton.setForeground(Color.decode(MAIN_TEXT));
        noButton.setFont(new Font(FONT, Font.BOLD, 20));
        receiptText = new JLabel ("Would You Like a Recipt?");
        receiptText.setForeground(Color.decode(MAIN_TEXT));
        receiptText.setFont(new Font(FONT, Font.BOLD, 20));
        receiptText.setHorizontalAlignment(JLabel.CENTER);

        //adjust size and set layout
        setPreferredSize (new Dimension (500, 300));
        setBackground(Color.decode(MAIN_PANEL));

        //add components
        checkBalPanel.add (transText);
        checkBalPanel.add (accHead);
        checkBalPanel.add (amount);
        checkBalPanel.add (yesButton);
        checkBalPanel.add (noButton);
        checkBalPanel.add (receiptText);

        add (checkBalPanel);

        //set component bounds (only needed by Absolute Positioning)
        transText.setBounds (0, 50, 400, 45);
        accHead.setBounds (0, 10, 400, 25);
        yesButton.setBounds (90, 190, 80, 55);
        noButton.setBounds (230, 190, 80, 55);
        receiptText.setBounds (0, 150, 400, 35);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Account #"+acc.getAccID());
                System.out.println("------------------------");
                System.out.println("Checked Balance");
                System.out.println("Balance:        "+acc.getBalance());
                System.out.println("------------------------");
                MainFrame m = new MainFrame();
                m.backButton();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame m = new MainFrame();
                m.backButton();
            }
        });
    }

}
