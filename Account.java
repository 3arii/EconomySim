public class Account {

  private String user_name;
  private double user_balance;
  private double user_annual_interest_rate;
  private double user_balance_in_bank;

  public Account(String name, double balance, double annual_interest_rate, double balance_in_bank) {
    user_name = name;
    user_balance = balance;
    user_annual_interest_rate = annual_interest_rate;
    user_balance_in_bank = balance_in_bank;
  }

  // Setter for balance
  public void setBalance(Double new_balance) {
    user_balance = new_balance;
  }

  // Getter for balance
  public double getBalance() {
    return user_balance;
  }

  // Setter for name
  public void setName(String new_name) {
    user_name = new_name;
  }

  // Getter for name
  public String getName() {
    return user_name;
  }

  // Getter for annual interest
  public double getAnnualInterestRate() {
    return user_annual_interest_rate;
  }

  // Setter for bank balance
  public void setBalanceinBank(double new_balance_in_bank) {
    user_balance_in_bank = new_balance_in_bank;
  }

  // Getter for bank balance
  public double getBalanceinBank() {
    return user_balance_in_bank;
  }
}
