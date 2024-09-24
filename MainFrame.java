import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import AtmSystem.*;
import java.util.HashMap;

public class MainFrame extends JFrame {

    Border brdButtons;
    private static JPanel mainPanel;
    static JPanel deposit;
    private static JPanel keypad;
    private static JPanel options;
    private static JPanel receipt;
    private static JLabel pinPrompt;
    private static JPanel screen;
    private static JPanel login;
    private static MainMenu accSel;
    static JTextField textfield = new JTextField();
    static JTextField ptextfield = new JTextField();
    static JButton keyButtons[] = new JButton[12];
    static JButton clear = new JButton("CLEAR");
    static JButton cancel = new JButton("CANCEL");
    static JButton ok = new JButton("OK");
    static JButton accessibility[] = new JButton[8];
    static JLabel pinValid = new JLabel();

    String MAIN_COLOR = "#5EA880";
    String MAIN_COLOR_DARK = "#2D5862";
    String MAIN_HOVER = "#458577";
    String MAIN_TEXT = "#FFFFFF";
    String MAIN_PANEL = "#4e4d4e";
    String MAIN_BACKGROUND = "#343435";
    String FONT = "Roboto";

    static CheckingAccount acc1 = new CheckingAccount(100, 1, 80, 10);
    static CheckingAccount acc2 = new CheckingAccount(100, 2, 50, 100);
    static SavingsAccount acc3 = new SavingsAccount(10, 3, 1000, 0.08, 30);
    static SavingsAccount acc4 = new SavingsAccount(0, 4, 10000, 0.08, 30);
    static Account[] accAll = { acc1, acc2, acc3, acc4 };
    static Customer cust1 = new Customer("John Doe", "12 Some St.", "6476487934", 1, accAll, 8888, 1111);
    static HashMap<Integer, Integer> bankDB = new HashMap<Integer, Integer>() {
        {
            put(8888, 1);
        }
    };
    static HashMap<Integer, Customer> custDB = new HashMap<Integer, Customer>() {
        {
            put(1, cust1);
        }
    };
    static Bank ATMBank = new Bank("Money Bank", "28 Wall St.", bankDB, custDB);
    static int cardNum = 8888;

    static Customer ATMCust;
    public static boolean logDone = false;

    public void initialize() {
        brdButtons = BorderFactory.createLineBorder(Color.decode(MAIN_COLOR), 7);

        setSize(1000, 600);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode(MAIN_BACKGROUND));
        keypad = new JPanel(new GridLayout(4, 3));
        keypad.setBounds(225, 350, 200, 200);
        options = new JPanel(new GridLayout(4, 1));
        options.setBounds(425, 350, 95, 200);
        receipt = new JPanel();
        receipt.setLayout(null);
        receipt.setBounds(750, 25, 225, 350);
        receipt.setBackground(Color.decode(MAIN_PANEL));

        JTextArea forDemo = new JTextArea("Card Num of inserted Card: 8888\n" +
                "PIN for Login: 1111\n\n" +
                "Bank Database has 1 Customer: cust1\n\n" +
                "cust1 has 4 Accounts: acc1, acc2, acc3, acc4\n\n" +
                "acc1: Checking, AccId = 1, Balance = 100, \n" + "    Withdraw Limit = 80, minDeposit = 10\n\n" +
                "acc2: Checking, AccId = 2, Balance = 100, \n" + "    Withdraw Limit = 50, minDeposit = 100\n\n" +
                "acc3: Savings, AccId = 3, Balance = 10, \n" + "    Withdraw Limit = 1000, interest = 0.08, \n"
                + "    manMonthlyWithdraws = 30\n\n" +
                "acc4: Savings, AccId = 4, Balance = 0, \n" + "    Withdraw Limit = 10000, interest = 0.08, \n"
                + "    manMonthlyWithdraws = 30\n\n" +
                "Receipts are printed to Console");
        forDemo.setEditable(false);
        forDemo.setForeground(Color.decode(MAIN_TEXT));
        forDemo.setBackground(Color.decode(MAIN_PANEL));
        forDemo.setBounds(0, 0, 225, 350);
        forDemo.setFont(new Font(FONT, Font.BOLD, 10));
        receipt.add(forDemo);

        pinPrompt = new JLabel("Please Enter Your PIN:");
        pinPrompt.setFont(new Font(FONT, Font.BOLD, 20));
        pinPrompt.setHorizontalAlignment(JLabel.CENTER);
        pinPrompt.setForeground(Color.decode(MAIN_TEXT));
        pinPrompt.setBounds(0, 60, 500, 100);

        pinValid.setFont(new Font(FONT, Font.BOLD, 20));
        pinValid.setHorizontalAlignment(JLabel.CENTER);
        pinValid.setForeground(Color.decode(MAIN_TEXT));
        pinValid.setBounds(0, 180, 500, 100);

        mainPanel.setLayout(null);

        screen = new JPanel();
        screen.setLayout(new CardLayout());
        screen.setBounds(115, 25, 500, 300);
        screen.setBackground(Color.decode(MAIN_PANEL));
        textfield.setFont(new Font(FONT, Font.PLAIN, 60));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setBounds(190, 150, 120, 50);
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.decode(MAIN_COLOR));
        login = new JPanel(null);
        login.setBackground(Color.decode(MAIN_PANEL));
        login.add(pinPrompt);
        login.add(pinValid);

        for (int i = 0; i < 12; i++) {
            if (i < 9) {
                keyButtons[i] = new JButton(String.valueOf(i + 1));
            } else if (i == 9) {
                keyButtons[i] = new JButton(String.valueOf("."));
            } else if (i == 10) {
                keyButtons[i] = new JButton(String.valueOf("0"));
            } else {
                keyButtons[i] = new JButton(String.valueOf("00"));
            }
        }
        for (int i = 0; i < 12; i++) {
            keypad.add(keyButtons[i]);
            keyButtons[i].addActionListener(keylisten);
            keyButtons[i].setBackground(Color.decode(MAIN_BACKGROUND));
            keyButtons[i].setForeground(Color.decode(MAIN_TEXT));
            keyButtons[i].setBorder(brdButtons);
            keyButtons[i].setFont(new Font(FONT, Font.PLAIN, 20));
        }

        clear.setBackground(Color.YELLOW);
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        clear.addActionListener(functionlisten);

        cancel.setBackground(Color.RED);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        cancel.addActionListener(functionlisten);

        ok.setBackground(Color.green);
        ok.setOpaque(true);
        ok.setBorderPainted(false);
        ok.addActionListener(functionlisten);

        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                accessibility[i] = new JButton("EXIT");
            } else if (i == 4) {
                accessibility[i] = new JButton("BACK");
            } else {
                accessibility[i] = new JButton("");
            }

        }
        int y = 35;
        int x = 35;
        for (int i = 0; i < 8; i++) {
            if (i < 4) {
                accessibility[i].setBounds(5, y, 100, 50);
                y = y + 75;
            } else {
                accessibility[i].setBounds(625, x, 100, 50);
                x = x + 75;

            }
            accessibility[i].setFont(new Font(FONT, Font.PLAIN, 20));
            accessibility[i].setBackground(Color.decode(MAIN_BACKGROUND));
            accessibility[i].setForeground(Color.decode(MAIN_TEXT));
            accessibility[i].setBorder(brdButtons);
        }
        accessibility[0].addActionListener(functionlisten);
        accessibility[4].addActionListener(functionlisten);
        for (int i = 0; i < 8; i++) {
            mainPanel.add(accessibility[i]);
        }

        options.add(cancel);
        options.add(clear);
        options.add(new JButton(""));
        options.add(ok);
        mainPanel.add(keypad);
        mainPanel.add(options);
        mainPanel.add(screen);
        login.add(textfield);
        mainPanel.add(receipt);
        screen.add(login, "Login");

        this.getContentPane().add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void backButton() {
        if (logDone) {
            if (accSel.getAccSelDone()) {
                accSel.setAmount("");
                accSel.goBack();
            } else {
                accSel.setVisible(false);
                screen.removeAll();
                screen.revalidate();
                screen.add(login, "Login");
                logDone = false;
            }
        }
    }

    static ActionListener keylisten = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((logDone == false) && (ptextfield.getText().length() < 4)) {
                if ((e.getSource() != keyButtons[11]) && (e.getSource() != keyButtons[9])) {
                    String currentText = textfield.getText();
                    String newChar = "*";
                    String pcurrentText = ptextfield.getText();
                    String pnewChar = e.getActionCommand();
                    textfield.setText(currentText + newChar);
                    ptextfield.setText(pcurrentText + pnewChar);
                }
            } else {
                String amount = accSel.getAmount();
                if (amount.length() < 6) {
                    if ((((e.getSource() == keyButtons[9]) && (countChar(amount, '.') < 1))
                            || ((e.getSource() != keyButtons[9]))
                                    && ((amount.indexOf(".") + 3 > amount.length()) || (amount.indexOf(".") == -1)))) {
                        String currentText = accSel.getAmount();
                        String newChar = e.getActionCommand();
                        accSel.setAmount(currentText + newChar);
                    }
                }

            }
        }
    };
    static ActionListener functionlisten = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String currentText = textfield.getText();
            String pcurrentText = ptextfield.getText();
            if (e.getSource() == clear) {
                if (logDone == false) {
                    if (currentText.length() != 0) {
                        String newText = currentText.substring(0, currentText.length() - 1);
                        textfield.setText(newText);
                        String pnewText = pcurrentText.substring(0, pcurrentText.length() - 1);
                        ptextfield.setText(pnewText);
                    }
                } else {
                    if (accSel.getAmount().length() != 0) {
                        String newAmount = accSel.getAmount().substring(0, accSel.getAmount().length() - 1);
                        accSel.setAmount(newAmount);
                    }
                }
            } else if (e.getSource() == ok) {
                if (logDone == false) {
                    if (ptextfield.getText().length() > 0) {
                        ATMCust = ATMBank.login(cardNum, Integer.parseInt(ptextfield.getText()));
                        if (ATMCust != null) {
                            accSel = new MainMenu(ATMCust);
                            screen.removeAll();
                            screen.revalidate();
                            screen.add(accSel);

                            logDone = true;
                        } else {
                            pinValid.setText("PIN is INVALID");
                        }

                    }
                }

            } else if (e.getSource() == cancel) {
                if (logDone == false) {
                    ptextfield.setText(null);
                    textfield.setText(null);
                } else {
                    accSel.setAmount("");
                }
            } else if (e.getSource() == accessibility[0]) {
                System.out.println("EXIT");
                System.exit(0);
            } else if (e.getSource() == accessibility[4]) {
                backButton();
            }
        }
    };

    public static int countChar(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();

    }

}