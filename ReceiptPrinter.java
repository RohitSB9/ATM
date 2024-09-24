
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import AtmSystem.*;

public class ReceiptPrinter extends JPanel {
  static JPanel accSel;
  Border brdButtons;
  Font fntTransaction, fntReciept, fntButton;
  JPanel pnlButtons, pnlText, pnlSouth, pnlLeft, pnlRight;
  JLabel lblTransaction, lblReciept;
  JButton btnYes, btnNo;

  String MAIN_COLOR = "#5EA880";
  String MAIN_COLOR_DARK = "#2D5862";
  String MAIN_HOVER = "#458577";
  String MAIN_TEXT = "#FFFFFF";
  String MAIN_PANEL = "#4e4d4e";
  String MAIN_BACKGROUND = "#343435";
  String FONT = "Roboto";



  public ReceiptPrinter(String transactionName, int transType, Account acc, Account recAcc, double amount, String transResult) {
    int accID = acc.getAccID(); 
    double newBal = acc.getBalance();
    brdButtons = BorderFactory.createLineBorder(new Color(94, 168, 128), 7);

    fntTransaction = new Font(FONT, Font.BOLD, 40);
    fntReciept = new Font(FONT, Font.PLAIN, 20);
    fntButton = new Font(FONT, Font.PLAIN, 30);

    pnlButtons = new JPanel();
    pnlText = new JPanel();
    pnlSouth = new JPanel();
    pnlLeft = new JPanel();
    pnlRight = new JPanel();

    pnlText.setBackground(Color.decode(MAIN_PANEL));
    pnlButtons.setBackground(Color.decode(MAIN_PANEL));
    pnlSouth.setBackground(Color.decode(MAIN_PANEL));
    pnlLeft.setBackground(Color.decode(MAIN_PANEL));
    pnlRight.setBackground(Color.decode(MAIN_PANEL));

    lblTransaction = new JLabel(transactionName + " " +transResult);
    lblTransaction.setFont(fntTransaction);
    lblTransaction.setOpaque(true);
    if(transResult == "Successful"){lblTransaction.setForeground(Color.GREEN);
    }else{
      lblTransaction.setForeground(Color.RED);
      lblTransaction.setFont(new Font(FONT, Font.BOLD, 25));}
    
    lblTransaction.setBackground(Color.decode(MAIN_PANEL));
    lblTransaction.setHorizontalAlignment(SwingConstants.CENTER);
    lblTransaction.setVerticalAlignment(SwingConstants.CENTER);

    lblReciept = new JLabel("Would You Like a Receipt?");
    lblReciept.setFont(fntReciept);
    lblReciept.setOpaque(true);
    lblReciept.setForeground(Color.decode(MAIN_TEXT));
    lblReciept.setBackground(Color.decode(MAIN_PANEL));
    lblReciept.setHorizontalAlignment(SwingConstants.CENTER);
    lblReciept.setVerticalAlignment(SwingConstants.CENTER);
    
    btnYes = new JButton("Yes");
    btnYes.setBorder(brdButtons);
    btnYes.setBackground(Color.decode(MAIN_PANEL));
    btnYes.setForeground(Color.WHITE);
    btnYes.setFont(fntButton);

    btnNo = new JButton("No");
    btnNo.setBorder(brdButtons);
    btnNo.setBackground(Color.decode(MAIN_PANEL));
    btnNo.setForeground(Color.WHITE);
    btnNo.setFont(fntButton);

    pnlText.setLayout(new BorderLayout());
    pnlText.add(lblTransaction, BorderLayout.NORTH);
    pnlText.add(lblReciept, BorderLayout.CENTER);
    pnlText.setPreferredSize(new Dimension(200,200));
    
    pnlButtons.setLayout(new GridLayout(1, 2, 90, 10));
    pnlButtons.setSize(500,200);
    pnlButtons.add(btnYes);
    pnlButtons.add(btnNo);    
    
    pnlSouth.setPreferredSize(new Dimension(0, 150));
    pnlLeft.setPreferredSize(new Dimension(100, 0));
    pnlRight.setPreferredSize(new Dimension(100, 0));


    setLayout(new BorderLayout());
    setSize(500,300);
    add(pnlText, BorderLayout.NORTH);
    add(pnlButtons, BorderLayout.CENTER);
    add(pnlSouth, BorderLayout.SOUTH);
    add(pnlLeft, BorderLayout.LINE_START);
    add(pnlRight, BorderLayout.LINE_END);

    btnYes.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Account #"+accID);
        System.out.println("------------------------");
        System.out.println(transactionName + " " +transResult);
        if(transType == 0){
          System.out.println("Deposit Amount :  "+amount);
        }else if (transType == 1){
          System.out.println("Withdraw Amount : "+amount);
        }else if (transType == 3){
          System.out.println("Transfer Amount : "+amount);
          System.out.println("Receiver Balance: "+newBal);

        }
        System.out.println("New Balance :    "+newBal);
        System.out.println("------------------------");

        MainFrame m = new MainFrame();
        m.backButton();
      }
    });

    btnNo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MainFrame m = new MainFrame();
        m.backButton();
        System.out.println("Reciept NOT printed");
      }
    });

  }

}