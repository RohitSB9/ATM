

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import AtmSystem.*;


public class TransferGUI extends JPanel {
    private JPanel selAmount;
    private JPanel selAcc;

    private JButton transferButton;
    private JLabel transText;
    private JLabel accHead;
    static JTextField amount = new JTextField();

    Border brdButtons;
    Font fntTitle;
    static JPanel pnlAccounts, pnlTitle, pnlSouth, pnlLeft, pnlRight, transactionPage;
    JLabel lblTitle;
    JButton[] accountButtons;
    String MAIN_COLOR = "#5EA880";
    String MAIN_COLOR_DARK = "#2D5862";
    String MAIN_HOVER = "#458577";
    String MAIN_TEXT = "#FFFFFF";
    String MAIN_PANEL = "#4e4d4e";
    String MAIN_BACKGROUND = "#343435";
    String FONT = "Roboto";

    public TransferGUI(Account[] accAll, Account acc, int selAccIndex) {
        int numAccounts = accAll.length;
        //Select amount
        selAmount = new JPanel();
        selAmount.setPreferredSize (new Dimension (400, 250));
        selAmount.setLayout (null);
        selAmount.setBackground(Color.decode(MAIN_PANEL));

        transferButton = new JButton ("Transfer");
        transferButton.setBackground(Color.decode(MAIN_COLOR));
        transferButton.setFont(new Font(FONT, Font.BOLD, 20));
        transferButton.setForeground(Color.decode(MAIN_TEXT));
        transText = new JLabel ("Please Enter the Amount:", SwingConstants.CENTER);
        transText.setForeground(Color.decode(MAIN_TEXT));
        transText.setFont(new Font(FONT, Font.PLAIN, 20));
        transText.setHorizontalAlignment(JLabel.CENTER);
        accHead = new JLabel ("Account #"+Integer.toString(acc.getAccID()), SwingConstants.CENTER);
        accHead.setForeground(Color.decode(MAIN_TEXT));
        accHead.setFont(new Font(FONT, Font.BOLD, 30));
        accHead.setHorizontalAlignment(JLabel.CENTER);
        amount.setFont(new Font(FONT, Font.PLAIN, 30));
        amount.setHorizontalAlignment(JTextField.CENTER);
        amount.setEditable(false);
        amount.setBounds(140,100,120,50);
        amount.setBackground(Color.BLACK);
        amount.setForeground(Color.decode(MAIN_COLOR));

        //add components
        selAmount.add (transferButton);
        selAmount.add (transText);
        selAmount.add (accHead);
        selAmount.add (amount);
        

        //set component bounds (only needed by Absolute Positioning)
        transferButton.setBounds (105, 180, 190, 65);
        transText.setBounds (0, 50, 400, 50);
        accHead.setBounds (0, 20, 400, 25);

        add(selAmount);
        
        //adjust size and set layout
        setPreferredSize (new Dimension (500, 300));
        setBackground(Color.decode(MAIN_PANEL));

        //add components
        selAcc = new JPanel();
        
        selAcc.setVisible(false);
        selAcc.setPreferredSize (new Dimension (400, 300));
        selAcc.setLayout (new BorderLayout());
        selAcc.setBackground(Color.decode(MAIN_PANEL));
        
        
        brdButtons = BorderFactory.createLineBorder(Color.decode(MAIN_COLOR), 7);

        fntTitle = new Font(FONT, Font.BOLD, 40);

        pnlAccounts = new JPanel();
        pnlTitle = new JPanel();
        pnlSouth = new JPanel();
        pnlLeft = new JPanel();
        pnlRight = new JPanel();

        pnlTitle.setBackground(Color.decode(MAIN_PANEL));
        pnlAccounts.setBackground(Color.decode(MAIN_PANEL));
        pnlSouth.setBackground(Color.decode(MAIN_PANEL));
        pnlLeft.setBackground(Color.decode(MAIN_PANEL));
        pnlRight.setBackground(Color.decode(MAIN_PANEL));

        lblTitle = new JLabel("Main Menu");
        lblTitle.setFont(fntTitle);
        lblTitle.setOpaque(true);
        lblTitle.setForeground(Color.decode(MAIN_TEXT));
        lblTitle.setBackground(Color.decode(MAIN_PANEL));

        accountButtons = new JButton[numAccounts];
;
        pnlTitle.add(lblTitle);
        pnlTitle.setPreferredSize(new Dimension(200,100));
        
        pnlAccounts.setLayout(new GridLayout(2, 4, 5, 5));
        pnlAccounts.setSize(460, 250);
        
        for (int i = 0; i < accountButtons.length; i++) {
            accountButtons[i] = new JButton("Account #" + Integer.toString(accAll[i].getAccID()));
            accountButtons[i].setBorder(brdButtons);
            accountButtons[i].setBackground(Color.decode(MAIN_BACKGROUND));
            accountButtons[i].setForeground(Color.decode(MAIN_TEXT));
            accountButtons[i].setPreferredSize(new Dimension(150, 10));
            accountButtons[i].setFont(new Font(FONT, Font.PLAIN, 14));
            pnlAccounts.add(accountButtons[i]);
        }
        accountButtons[selAccIndex].setVisible(false);
        
        pnlSouth.setPreferredSize(new Dimension(0, 50));
        pnlLeft.setPreferredSize(new Dimension(10, 0));
        pnlRight.setPreferredSize(new Dimension(10, 0));
        
        selAcc.add(pnlTitle, BorderLayout.NORTH);
        selAcc.add(pnlAccounts, BorderLayout.CENTER);
        selAcc.add(pnlSouth, BorderLayout.SOUTH);
        selAcc.add(pnlLeft, BorderLayout.LINE_START);
        selAcc.add(pnlRight, BorderLayout.LINE_END);

        for (int i = 0; i < accountButtons.length; i++) {
        accountButtons[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();
                int accIndex = Integer.parseInt(btn.getText().split("#")[1]) - 1;
                boolean tranCheck = acc.transfer(Double.parseDouble(amount.getText()), accAll[accIndex]);
                selAcc.setVisible(false); 
                if(tranCheck){                
                    JPanel receipt = new ReceiptPrinter("Transfer",3,acc,accAll[accIndex],Double.parseDouble(amount.getText()),"Successful");
                    add(receipt);
                }else{
                    JPanel receipt = new ReceiptPrinter("Transfer",3,acc,accAll[accIndex],Double.parseDouble(amount.getText()),"Unsuccessful");
                    add(receipt);
                }
                amount.setText("");
            }
        });
        }
        add(selAcc);

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selAmount.setVisible(false);
                selAcc.setVisible(true);
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