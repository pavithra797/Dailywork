package banking.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import Repository.AccountRepository;
import banking.exception.BankingException;
import banking.model.Account;
import banking.service.BankService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankServiceTest {

	@Mock
	private AccountRepository repository;

	@InjectMocks
	private BankService bankService;

	private Account account;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		account = new Account(1, "Amit", 1000);
	}

	@Test
	void testDeposit() {
		when(repository.findById(1)).thenReturn(account);
		bankService.deposit(1, 500);
		assertEquals(1500, account.getBalance());
		verify(repository).save(account);
	}

	@Test
	void testWithdraw() {
		when(repository.findById(1)).thenReturn(account);
		bankService.withdraw(1, 500);
		assertEquals(500, account.getBalance());
		verify(repository).save(account);
	}

	@Test
	void testCreateAccount() {
		when(repository.findById(2)).thenReturn(null);
		Account newAccount = new Account(2, "Riya", 500);
		doNothing().when(repository).save(any(Account.class));

		bankService.createAccount(2, "Riya", 500);

		verify(repository).save(any(Account.class));
	}

	@Test
	void testCreateDuplicateAccount() {
		when(repository.findById(1)).thenReturn(account);
		assertThrows(BankingException.class, () -> bankService.createAccount(1, "Amit", 1000));
		verify(repository, never()).save(any());
	}

	@Test
	void testGetAccount() {
		when(repository.findById(1)).thenReturn(account);
		Account result = bankService.getAccount(1);
		assertEquals(account, result);
	}

	@Test
	void testGetNonExistingAccount() {
		when(repository.findById(999)).thenReturn(null);
		assertThrows(BankingException.class, () -> bankService.getAccount(999));
	}

	@Test
	void testMultipleDepositsAndWithdrawals() {
		when(repository.findById(1)).thenReturn(account);

		bankService.deposit(1, 100);
		bankService.deposit(1, 200);
		bankService.withdraw(1, 50);

		assertEquals(1250, account.getBalance());
		verify(repository, times(3)).save(account);
	}
}