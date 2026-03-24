package banking.model;

public class Account {

	private int accountNumber;
	private String holderName;
	private double balance;

	public Account(int accoupntNumber, String holderName, double balance) {
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("Invalid deposit");
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount > balance)
			throw new IllegalArgumentException("Insufficient balance");
		balance -= amount;
	}

}
