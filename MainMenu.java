
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.Border;

import javax.swing.*;
import AtmSystem.*;

public class MainMenu extends JPanel {
  Border brdButtons;
  Font fntTitle;
  static JPanel pnlAccounts, pnlTitle, pnlSouth, pnlLeft, pnlRight;
  static TransactionPage transactionPage;
  JLabel lblTitle;
  JButton[] accountButtons;

  String MAIN_COLOR = "#5EA880";
  String MAIN_COLOR_DARK = "#2D5862";
  String MAIN_HOVER = "#458577";
  String MAIN_TEXT = "#FFFFFF";
  String MAIN_PANEL = "#4e4d4e";
  String MAIN_BACKGROUND = "#343435";
  String FONT = "Roboto";

  public static boolean accSelDone = false;

  public MainMenu(Customer cust) {
    Account[] acc = cust.getAccounts();
    int numAccounts = acc.length;

    setBackground(Color.decode(MAIN_PANEL));
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

    setLayout(new BorderLayout());
    pnlTitle.add(lblTitle);
    pnlTitle.setPreferredSize(new Dimension(200, 100));

    pnlAccounts.setLayout(new GridLayout(2, 4, 5, 5));
    pnlAccounts.setSize(460, 250);

    for (int i = 0; i < accountButtons.length; i++) {
      accountButtons[i] = new JButton("Account #" + Integer.toString(acc[i].getAccID()));
      accountButtons[i].setBorder(brdButtons);
      accountButtons[i].setBackground(Color.decode(MAIN_BACKGROUND));
      accountButtons[i].setForeground(Color.decode(MAIN_TEXT));
      accountButtons[i].setPreferredSize(new Dimension(150, 10));
      accountButtons[i].setFont(new Font(FONT, Font.PLAIN, 14));
      pnlAccounts.add(accountButtons[i]);
    }

    pnlSouth.setPreferredSize(new Dimension(0, 50));
    pnlLeft.setPreferredSize(new Dimension(10, 0));
    pnlRight.setPreferredSize(new Dimension(10, 0));

    add(pnlTitle, BorderLayout.NORTH);
    add(pnlAccounts, BorderLayout.CENTER);
    add(pnlSouth, BorderLayout.SOUTH);
    add(pnlLeft, BorderLayout.LINE_START);
    add(pnlRight, BorderLayout.LINE_END);

    for (int i = 0; i < accountButtons.length; i++) {
      accountButtons[i].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JButton btn = (JButton) e.getSource();
          int accIndex = Integer.parseInt(btn.getText().split("#")[1]) - 1;
          transactionPage = new TransactionPage(acc[accIndex], accIndex, acc);
          pnlAccounts.setVisible(false);
          pnlLeft.setVisible(false);
          pnlRight.setVisible(false);
          pnlSouth.setVisible(false);
          pnlTitle.setVisible(false);
          add(transactionPage);

          accSelDone = true;
        }
      });
    }
  }

  public static boolean getAccSelDone() {
    return accSelDone;
  }

  public static void goBack() {
    transactionPage.setVisible(false);
    pnlAccounts.setVisible(true);
    pnlLeft.setVisible(true);
    pnlRight.setVisible(true);
    pnlSouth.setVisible(true);
    pnlTitle.setVisible(true);
    accSelDone = false;
  }

  public static void setAmount(String str) {
    transactionPage.setAmount(str);
  }

  public static String getAmount() {
    return transactionPage.getAmount();
  }

}