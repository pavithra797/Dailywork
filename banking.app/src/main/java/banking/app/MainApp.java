package banking.app;

import java.util.Scanner;

import Repository.AccountRepository;
import Repository.FileAccountRepository;
import banking.exception.BankingException;
import banking.service.BankService;

public class MainApp {

    public static void main(String[] args) {

        AccountRepository repository = new FileAccountRepository();
        BankService bank = new BankService(repository);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Create 2.Deposit 3.Withdraw 4.Check 5.Exit");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Acc No: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Balance: ");
                        double bal = sc.nextDouble();
                        bank.createAccount(id, name, bal);
                        break;

                    case 2:
                        System.out.print("Acc No: ");
                        bank.deposit(sc.nextInt(), sc.nextDouble());
                        break;

                    case 3:
                        System.out.print("Acc No: ");
                        bank.withdraw(sc.nextInt(), sc.nextDouble());
                        break;

                    case 4:
                        System.out.print("Acc No: ");
                        System.out.println(bank.getAccount(sc.nextInt()).getBalance());
                        break;

                    case 5:
                        return;
                }
            } catch (BankingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}