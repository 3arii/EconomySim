import java.util.Scanner;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.util.Random;

public class Bank {

  private static String user_name;
  private static Account user_account;
  private static double user_balance;
  private static double user_balance_in_bank;
  private static double money_to_tax;
  private static int random_bank_money;
  private static double company_value;
  private static int company_shares;
  private static int available_shares;
  private static double shares_fluctuation;

  public static void main(String[] args) {
    // Initialize the Scanner
    Scanner get_input = new Scanner(System.in);

    // Initialize the random module
    Random randomizer = new Random();

    // Initialize the company shares you own
    company_shares = 0;

    // Initialize the company value
    company_value = 100;

    // Initialize the available shares
    available_shares = 10;

    boolean confirming_name = true;
    while (confirming_name == true) {
      System.out.println("Enter your name:");
      user_name = get_input.nextLine();

      System.out.println("Your name is set to be " + user_name + " do you confirm it(y/n)?");
      String name_confirmation = get_input.nextLine();

      if (name_confirmation.equals("y")) {
        confirming_name = false;
      } else if (name_confirmation.equals("n")) {
        confirming_name = true;
      } else {
        System.out.println("Please enter either y or n");
        confirming_name = true;
      }
    }
    user_account = new Account(user_name, 1000, 24, 0);
    System.out.println("Proceed to tutorial?(y/n)");
    String tutorial_confirmation = get_input.nextLine();

    if (tutorial_confirmation.equals("y")) {
      System.out.println(
          "This is a game of investments, balance and money.\nYou have two money keeping methods: on yourself and in the bank.\n If you choose yourself you will not be able to take the annual interest rate which will give you 0.001% of your money every turn, but it can also give you up to 10000 dolars by chance.\n but if, you put it in a bank you will have to pay more tax than usual.\n You can invest your money as well in three companies, which you will either lose or gain money on random chance.");
    }
    boolean game = true;
    while (game) {
      random_bank_money = randomizer.nextInt(100 + 100) - 100;
      shares_fluctuation = randomizer.nextDouble();

      user_balance = user_account.getBalance();
      user_balance_in_bank = user_account.getBalanceinBank();

      if (user_balance_in_bank > 0) {
        money_to_tax = (user_balance * 0.01) + (user_balance_in_bank * 0.01) + 1;
      } else {
        money_to_tax = (user_balance * 0.01) + (user_balance_in_bank * 0.01);
      }

      if (user_balance >= money_to_tax) {
        user_balance -= money_to_tax;
      } else if (user_balance_in_bank >= money_to_tax) {
        user_balance_in_bank -= money_to_tax;
      } else {
        System.out.println("You are bankrupt, you have failed the game.");
        game = false;
      }

      if (user_balance_in_bank > 0) {
        double interest_rate = (user_balance_in_bank * 0.001);
        user_balance_in_bank += interest_rate;
      }

      if (user_balance_in_bank > 0) {
        if (random_bank_money == 0) {
          user_balance_in_bank += 10000;
        }
      }

      if (shares_fluctuation > 0.5) {
        double share_to_increase = (company_value * 0.1);
        company_value += share_to_increase;
      } else {
        double share_to_decrease = (company_value * 0.1);
        company_value -= share_to_decrease;
      }

      System.out.println("User name: " + user_account.getName());
      System.out.println("\nUser balance: " + user_account.getBalance());
      System.out.println("\nUser balance in bank: " + user_account.getBalanceinBank());
      System.out.println(
          "\nPlease enter your command(deposit: deposit money/ withdraw: withdraw money/ invest: see all the companie/ exit: exit the game)");
      String command = get_input.nextLine();

      if (command.equals("deposit")) {
        System.out.println("Enter the amount of money you want to deposit:");
        double number_to_deposit = get_input.nextDouble();

        if (number_to_deposit != 0 && number_to_deposit <= user_balance) {
          deposit(number_to_deposit);
        } else {
          if (number_to_deposit == 0.0) {
            System.out.println("This is quite useless.");
          } else if (number_to_deposit >= user_balance) {
            System.out.println("You are broke.");
          } else {
            System.out.println("Please enter a monetary value.");
          }
        }
      } else if (command.equals("exit")) {
        game = false;
      } else if (command.equals("withdraw")) {
        System.out.println("Enter the amount of money you want to withdraw:");
        double number_to_withdraw = get_input.nextDouble();

        if (number_to_withdraw != 0 && number_to_withdraw <= user_balance_in_bank) {
          withdraw(number_to_withdraw);
        } else {
          if (number_to_withdraw == 0.0) {
            System.out.println("This is quite useless");
          } else if (number_to_withdraw > user_balance_in_bank) {
            System.out.println("You are broke");
          } else {
            System.out.println("Please enter a monetary value");
          }
        }
      } else if (command.equals("invest")) {
        boolean invest_screen = true;
        while (invest_screen) {
          System.out.println("Your Company Shares: " + company_shares);
          System.out.println("Microsoft Shares Available: " + available_shares);
          System.out.println("Microsoft Share Value: " + company_value);

          System.out.println("Enter your command(Buy: buy shares/ Sell: sell shares/ Exit: Exit shares menu)");
          String invest_menu_command = get_input.nextLine();

          if (invest_menu_command.equals("buy")) {
            System.out.println("Enter the amount of shares you wish to buy:");
            int share_to_buy = get_input.nextInt();

            if (available_shares > 0 && user_balance_in_bank > (company_value * share_to_buy)) {
              BuyShares(share_to_buy);
              user_account.setBalanceinBank(user_balance_in_bank - company_value);
            } else if (available_shares == 0) {
              System.out.println("You own this company");
            } else if (user_balance_in_bank < company_value) {
              System.out.println("You can only buy shares with money in your bank or you don't have enough money.");
            } else {
              System.out.println("Please enter a number!");
            }
          } else if (invest_menu_command.equals("sell")) {
            if (company_shares > 0) {
              System.out.println("Enter the amount you want to sell: ");
              int share_to_sell = get_input.nextInt();

              SellShares(share_to_sell);
              user_account.setBalanceinBank(user_balance_in_bank + company_value);
            } else if (company_shares == 0) {
              System.out.println("You don't own any shares of this company");
            } else {
              System.out.println("An error occured, please continue.");
            }
          } else if (invest_menu_command.equals("exit")) {
            invest_screen = false;
          }
        }
      }
    }
  }

  // Deposit
  public static void deposit(Double money_to_deposit) {
    user_account.setBalanceinBank(user_balance_in_bank + money_to_deposit);
    user_account.setBalance(user_balance - money_to_deposit);
  }

  // Withdraw
  public static void withdraw(double money_to_withdraw) {
    user_account.setBalance(user_balance + money_to_withdraw);
    user_account.setBalanceinBank(user_balance_in_bank - money_to_withdraw);
  }

  // Buy shares
  public static void BuyShares(int share_to_buy) {
    company_shares += share_to_buy;
    available_shares -= share_to_buy;
  }

  // Sell shares
  public static void SellShares(int share_to_sell) {
    available_shares += share_to_sell;
    company_shares -= share_to_sell;
  }
}
