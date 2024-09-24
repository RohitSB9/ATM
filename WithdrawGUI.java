


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import AtmSystem.*;


public class WithdrawGUI extends JPanel {
    private JPanel withdrawPanel;
    private JButton withdrawButton;
    private JLabel transText;
    private JLabel accHead;
    static JTextField amount = new JTextField("");
    String MAIN_COLOR = "#5EA880";
    String MAIN_COLOR_DARK = "#2D5862";
    String MAIN_HOVER = "#458577";
    String MAIN_TEXT = "#FFFFFF";
    String MAIN_PANEL = "#4e4d4e";
    String MAIN_BACKGROUND = "#343435";
    String FONT = "Roboto";

    public WithdrawGUI(Account acc) {
        withdrawPanel = new JPanel();
        withdrawPanel.setPreferredSize (new Dimension (400, 300));
        withdrawPanel.setLayout (null);
        withdrawPanel.setBackground(Color.decode(MAIN_PANEL));
        //construct components
        withdrawButton = new JButton ("Withdraw");
        withdrawButton.setBackground(Color.decode(MAIN_COLOR));
        withdrawButton.setFont(new Font(FONT, Font.BOLD, 20));
        transText = new JLabel ("Please Enter the Amount:", SwingConstants.CENTER);
        transText.setForeground(Color.decode(MAIN_TEXT));
        transText.setFont(new Font(FONT, Font.BOLD, 20));
        transText.setHorizontalAlignment(JLabel.CENTER);
        accHead = new JLabel ("Account #"+Integer.toString(acc.getAccID()), SwingConstants.CENTER);
        accHead.setFont(new Font(FONT, Font.BOLD, 30));
        accHead.setForeground(Color.decode(MAIN_TEXT));
        accHead.setHorizontalAlignment(JLabel.CENTER);
        amount.setFont(new Font(FONT, Font.PLAIN, 30));
        amount.setHorizontalAlignment(JTextField.CENTER);
        amount.setEditable(false);
        amount.setBounds(140,150,120,50);
        amount.setBackground(Color.BLACK);
        amount.setForeground(Color.decode(MAIN_COLOR));

        //adjust size and set layout
        setPreferredSize (new Dimension (500, 300));
        setBackground(Color.decode(MAIN_PANEL));

        //add components
        withdrawPanel.add (withdrawButton);
        withdrawPanel.add (transText);
        withdrawPanel.add (accHead);
        withdrawPanel.add (amount);

        add (withdrawPanel);
        

        //set component bounds (only needed by Absolute Positioning)
        withdrawButton.setBounds (105, 215, 190, 65);
        transText.setBounds (0, 80, 400, 50);
        accHead.setBounds (0, 20, 400, 25);


        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean witSucc = acc.withdraw(Double.parseDouble(amount.getText()));
                withdrawPanel.setVisible(false);
                if(witSucc){
                    JPanel receipt = new ReceiptPrinter("Withdraw",1,acc,null,Double.parseDouble(amount.getText()),"Successful");
                    add(receipt);
                }else{
                    JPanel receipt = new ReceiptPrinter("Withdraw",1,acc,null,Double.parseDouble(amount.getText()),"Unsuccessful");
                    add(receipt);
                }
                amount.setText("");
            }
                
        });
    }

    public void setAmount(String str){
        amount.setText(str);
    }

    public String getAmount(){
        return amount.getText();
    }


}
