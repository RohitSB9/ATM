package AtmSystem;

public class SavingsAccount extends Account {
  
  private double intrestRate;
  private int maxMonthlyWithdraws, withdrawCount;

  public SavingsAccount(double balance, int accID, double withdrawLimit, double intrestRate, int maxMonthlyWithdraws) {
    super(balance, accID, withdrawLimit);
    this.intrestRate = intrestRate;
    this.maxMonthlyWithdraws = maxMonthlyWithdraws;
    this.withdrawCount = 0;
  }

  public double getIntrestRate() {
    return intrestRate;
  }

  public void setIntrestRate(double intrestRate) {
    this.intrestRate = intrestRate;
  }

  public boolean deposit(double cash) {
    setBalance(getBalance() + cash);
    return true;
  }

  public boolean withdraw(double cash) {
    if (cash >= getBalance() || cash > getWithdrawLimit() || withdrawCount >= maxMonthlyWithdraws) {
      return false;
    }
    setBalance(getBalance() - cash);
    withdrawCount++;
    return true;
  }
}
