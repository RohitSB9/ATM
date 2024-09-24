package AtmSystem;

public class Account {
  private double balance, withdrawLimit;
  private int accID;

  public Account(double balance, int accID, double withdrawLimit) {
    this.balance = balance;
    this.accID = accID;
    this.withdrawLimit = withdrawLimit;
  }

  public double getBalance() {
    return balance;
  }

  public int getAccID() {
    return accID;
  }

  public double getWithdrawLimit() {
    return withdrawLimit;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setWithdrawLimit(double withdrawLimit) {
    this.withdrawLimit = withdrawLimit;
  }

  public boolean deposit(double cash) {
    return false;
  }

  public boolean withdraw(double cash) {
    return false;
  }

  public boolean transfer(double cash, Account otherAcc) {
    if (cash >= this.balance) {
      return false;
    }
    otherAcc.setBalance(otherAcc.getBalance() + cash);
    this.setBalance(this.balance - cash);
    return true;

  }

}
