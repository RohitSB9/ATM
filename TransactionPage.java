
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import AtmSystem.*;


public class TransactionPage extends JPanel {
    Border brdButtons;
    static JPanel panel;
    JLabel label_TransInfo;
    JLabel label_AccInfo;
    JButton deposit, withdraw, checkBalance, transfer;
    public static DepositGUI depositPanel ;
    public static WithdrawGUI withdrawPanel ;
    public static CheckBalanceGUI checkBalPanel ;
    public static TransferGUI transferPanel ;

    String MAIN_COLOR = "#5EA880";
    String MAIN_COLOR_DARK = "#2D5862";
    String MAIN_HOVER = "#458577";
    String MAIN_TEXT = "#FFFFFF";
    String MAIN_PANEL = "#4e4d4e";
    String MAIN_BACKGROUND = "#343435";
    String FONT = "Roboto";

    static boolean transSelDone = false;
    static boolean DSelDone = false;
    static boolean WSelDone = false;
    static boolean CSelDone = false;
    static boolean TSelDone = false;




    public TransactionPage(Account acc, int accIndex, Account[] accAll) {

        String accID = Integer.toString(acc.getAccID());
        brdButtons = BorderFactory.createLineBorder(Color.decode(MAIN_COLOR), 7);

        panel = new JPanel();
        panel.setPreferredSize (new Dimension (500, 300));
        panel.setLayout(null);
        panel.setBackground(Color.decode(MAIN_PANEL));
        
        label_AccInfo = new JLabel("Account #"+accID);
        label_AccInfo.setFont(new Font("", Font.PLAIN, 20));
        label_AccInfo.setBounds(0, 5, 500, 60);
        label_AccInfo.setForeground(Color.decode(MAIN_TEXT));
        label_AccInfo.setFont(new Font(FONT, Font.BOLD, 30));
        label_AccInfo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label_AccInfo);
        setSize(500, 300);
        setBackground(Color.decode(MAIN_PANEL));

        label_TransInfo = new JLabel("Please Select A Transaction:");
        label_TransInfo.setFont(new Font("", Font.PLAIN, 20));
        label_TransInfo.setBounds(0, 30, 500, 100);
        label_TransInfo.setForeground(Color.decode(MAIN_TEXT));
        label_TransInfo.setFont(new Font(FONT, Font.PLAIN, 25));
        label_TransInfo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label_TransInfo);

        deposit = new JButton("Deposit");
        deposit.setBounds(50, 100, 180, 80);
        deposit.setBackground(Color.decode(MAIN_BACKGROUND));
        deposit.setForeground(Color.decode(MAIN_TEXT));
        deposit.setBorder(brdButtons);
        deposit.setFont(new Font(FONT, Font.PLAIN, 20));
        panel.add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(250, 100, 180, 80);
        withdraw.setBackground(Color.decode(MAIN_BACKGROUND));
        withdraw.setForeground(Color.decode(MAIN_TEXT));
        withdraw.setBorder(brdButtons);
        withdraw.setFont(new Font(FONT, Font.PLAIN, 20));
        panel.add(withdraw);

        checkBalance = new JButton("Check Balance");
        checkBalance.setBounds(50, 200, 180, 80);
        checkBalance.setBackground(Color.decode(MAIN_BACKGROUND));
        checkBalance.setForeground(Color.decode(MAIN_TEXT));
        checkBalance.setBorder(brdButtons);
        checkBalance.setFont(new Font(FONT, Font.PLAIN, 20));
        panel.add(checkBalance);

        transfer = new JButton("Transfer");
        transfer.setBounds(250, 200, 180, 80);
        transfer.setBackground(Color.decode(MAIN_BACKGROUND));
        transfer.setForeground(Color.decode(MAIN_TEXT));
        transfer.setBorder(brdButtons);
        transfer.setFont(new Font(FONT, Font.PLAIN, 20));
        panel.add(transfer);
        add(panel);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                depositPanel = new DepositGUI(acc);
                add(depositPanel);
                DSelDone = true;
                transSelDone = true;
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                withdrawPanel = new WithdrawGUI(acc);
                add(withdrawPanel);
                WSelDone = true;
                transSelDone = true;
            }
        });
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                checkBalPanel = new CheckBalanceGUI(acc);
                add(checkBalPanel);
                CSelDone = true;
                transSelDone = true;
            }
        });
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                transferPanel = new TransferGUI(accAll, acc, accIndex);
                add(transferPanel);
                TSelDone = true;
                transSelDone = true;
            }
        });
    }

    public void setAmount(String str){
        if(DSelDone){
            depositPanel.setAmount(str);
        }
        if(WSelDone){
            withdrawPanel.setAmount(str);
        }
        if(TSelDone){
            transferPanel.setAmount(str);
        }
    }

    public String getAmount(){
        if(DSelDone){
            return depositPanel.getAmount();
        }
        if(WSelDone){
            return withdrawPanel.getAmount();
        }
        if(TSelDone){
            return transferPanel.getAmount();
        }
        return "0";
    }

    public static boolean getTransSelDone(){
        return transSelDone;
    }
    public static void goBackTrans(){
        if(transSelDone){
            if(DSelDone){
                depositPanel.setVisible(false);
            }
            if(WSelDone){
                withdrawPanel.setVisible(false);
            }
            if(CSelDone){
                checkBalPanel.setVisible(false);
            }
            if(TSelDone){
                transferPanel.setVisible(false);
            }
            transSelDone = false;
            DSelDone = false;
            WSelDone = false;
            CSelDone = false;
            TSelDone = false;
            panel.setVisible(true);
        }
        
    }

    public static void main(String[] args) {
    }
}