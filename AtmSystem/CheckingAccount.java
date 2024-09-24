package AtmSystem;


public class CheckingAccount extends Account {
  private double minDepositAmount;

  public CheckingAccount(double balance, int accID, double withdrawLimit, double minDepositAmount) {
    super(balance, accID, withdrawLimit);
    this.minDepositAmount = minDepositAmount;
  }
  
  public boolean deposit(double cash) {
    if (cash < minDepositAmount) {
      return false;
    }
    setBalance(getBalance() + cash);
    return true;
  }

  public boolean withdraw(double cash) {
    if (cash >= getBalance() || cash > getWithdrawLimit()) {
      return false;
    }
    setBalance(getBalance() - cash);
    return true;
  }
}
