import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        //Create a TimeZone object for PHT
        TimeZone tz = TimeZone.getTimeZone("Asia/Manila");
        
        //Set the time zone for the date object
        Calendar calendar = Calendar.getInstance(tz);
        Date date = calendar.getTime();
        
        //Declared Scanner
        Scanner input = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("Php ###,###,###.00");
        
        int[] accountNumbers = new int[10];
        double[] accountBalances = new double[10];
        int[] accountPins = new int [10];
        List<String> transactionHistory = new ArrayList<>();
        
        //Declared Variables
        int userChoice;
        int accountNumber;
        int accountPin;
        double depositAmount;
        double withdrawAmount;
        double checkBalance;
        String Line = "---------------------------------------";
        
        //Input Sreen
        while (true) {
            loader();
           System.out.println();
           System.out.println("■■■■■ Welcome to our Bank Management System. ■■■■■");
           System.out.println(Line);
           System.out.println("[1] Create an Account " + "         |");
           System.out.println(Line);
           System.out.println("[2] Deposit Money " + "             |");
           System.out.println(Line);
           System.out.println("[3] Withdraw Money " + "            |");
           System.out.println(Line);
           System.out.println("[4] Check Balance " + "             |");
           System.out.println(Line);
           System.out.println("[5] View Transaction History " + "  |");
           System.out.println(Line);
           System.out.println("[6] Exit " + "                      |");
           System.out.println(Line);
           System.out.print("Please enter your Choice: ");
           userChoice = input.nextInt();
           
           //Output Screen
           switch (userChoice) {
               
               //Account Creation
               case 1:
                   System.out.println();
                   System.out.print("Please enter your 10 - digit account number: ");
                   accountNumber = input.nextInt();
                   if (accountNumber < 100000000 || accountNumber > 999999999) {
                       System.out.println("Error: Invalid account number. Please enter a 10 - digit number.");
                       break;
                   }
                   System.out.print("Please enter your 4 - digit pin number: ");
                   accountPin = input.nextInt();
                   if (accountPin < 1000 || accountPin > 9999) {
                       System.out.println("Error: Invalid pin number. Please enter 4 - digit number");
                       break;
                   }
                   for (int i = 0; i < accountNumbers.length; i++) {
                       if (accountNumbers[i] == 0) {
                           accountNumbers[i] = accountNumber;
                           accountPins[i] = accountPin;
                           System.out.println(Line + "|");
                           System.out.println();
                           System.out.println("▬▬▬▬▬ Your account has been created succesfully! ▬▬▬▬▬");
                           System.out.println();
                           System.out.println(Line + "|");
                           break;
                       }
                   }
                   break;
                   
               //Deposit Panel
               case 2:
                   System.out.println();
                   System.out.print("Please enter your 10 - digit account number: ");
                   accountNumber = input.nextInt();
                   int accountIndex = -1;
                   for (int i = 0; i < accountNumbers.length; i++) {
                       if (accountNumbers[i] == accountNumber) {
                           accountIndex = i;
                           break;
                       }
                   }
                   if (accountIndex == -1) {
                       System.out.println(Line + "|");
                       System.out.println("Error: Account not found.");
                       break;
                   }
                   System.out.print("Please enter your 4 - digit pin number: ");
                   accountPin = input.nextInt();
                   if (accountPins[accountIndex] != accountPin) {
                       System.out.println("Error: Invalid pin number. Please try again.");
                       break;
                   }
                   System.out.println();
                   System.out.print("Please enter the amount you wish to deposit: ");
                   depositAmount = input.nextDouble();
                   accountBalances[accountIndex] += depositAmount;
                   transactionHistory.add("Deposit of " + formatter.format(depositAmount) + " to account number " + accountNumber + " at " + calendar.getTime());
                   System.out.println(Line + "|");
                   System.out.println("Successfully deposited " + formatter.format(depositAmount) + " to account number " + accountNumber);
                   System.out.println(Line + "|");
                   break;

               //Withdraw Panel
               case 3:
                   System.out.println();
                   System.out.print("Please enter your 10 - digit account number: ");
                   accountNumber = input.nextInt();
                   accountIndex = -1;
                   for (int i = 0; i < accountNumbers.length; i++) {
                       if (accountNumbers[i] == accountNumber) {
                           accountIndex = i;
                           break;
                       }
                   }
                   if (accountIndex == -1) {
                       System.out.println(Line + "|");
                       System.out.println("Error: Account not found.");
                       break;
                   }
                   System.out.println();
                   System.out.print("Please enter the amount you wish to withdraw: ");
                   withdrawAmount = input.nextDouble();
                   if (withdrawAmount > accountBalances[accountIndex]) {
                       System.out.println(Line + "|");
                       System.out.println("Error: Insufficient balance.");
                       break;
                   }
                   accountBalances[accountIndex] -= withdrawAmount;
                   transactionHistory.add("Withdraw of " + formatter.format(withdrawAmount) + " from account number " + accountNumber + " at " + calendar.getTime());
                   System.out.println(Line + "|");
                   System.out.println("Succesfully withdrew " + formatter.format(withdrawAmount) + " from account number " + accountNumber);
                   System.out.println(Line + "|");
                   break;
                   
               //Check balance
               case 4:
                   System.out.println();
                   System.out.print("Please enter your 10 - digit account number: ");
                   accountNumber = input.nextInt();
                   accountIndex = -1;
                   for (int i = 0; i < accountNumbers.length; i++) {
                       if (accountNumbers[i] == accountNumber) {
                           accountIndex = i;
                           break;
                       }
                   }
                   if (accountIndex == -1) {
                       System.out.println(Line + "|");
                       System.out.println("Error: Account not found.");
                       break;
                   }
                   System.out.println(Line + "|");
                   System.out.println("Account Number: " + accountNumber);
                   System.out.println("Balance: " + formatter.format(accountBalances[accountIndex]));
                   System.out.println(Line + "|");
                   break;

               //View Transaction History
               case 5:
                   System.out.println();
                   System.out.print("Please enter your 10 - digit account number: ");
                   accountNumber = input.nextInt();
                   accountIndex = -1;
                   for (int i = 0; i < accountNumbers.length; i++) {
                       if (accountNumbers[i] == accountNumber) {
                           accountIndex =i;
                           break;
                       } 
                   }
                   if (accountIndex == -1) {
                       System.out.println(Line + "|");
                       System.out.println("Error: Account not found.");
                       break;
                   }
                   System.out.println(Line + "|");
                   System.out.println("Transaction History for Account Number " + accountNumber + ":");
                   for (String history : transactionHistory) {
                       if (history.contains(String.valueOf(accountNumber))) {
                           System.out.println(history);
                       }
                   }
                   System.out.println(Line + "|");
                   break;
                   
               //Exit
               case 6:
                   System.out.println();
                   System.out.println("Thank your for using our Bank Management System. Have a good day!");
                   System.exit(0);
                   break;
                   
               //Invalid Choice
               default:
               System.out.println();
               System.out.println("Error: Invalid choice. Please enter a valid option.");
               break;
           }
        }
    }
    
        //Loading Bar
        static void loader(){
        System.out.print("Processing > ");
        for (int i = 0; i <= 15; i++) {
            
            try {
                System.out.print("■");
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 15) {
                System.out.println(" < Complete!");
            }
        }
    }
}
