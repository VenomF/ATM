package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Account;
import classes.Menu;

public class MenuTest {
	
	Account accOne;
	Account accTwo;
	int accNum;
	double balance;
	double balancePreTransfer;
	
	@Before
	public void setUp() throws Exception {
		accOne=new Account(1111, "Faruk", 200);
		accTwo=new Account(2222, "Faruk", 100);
	}

	@Test
	public void transferFunds_passValidInfo_transferFundsFromOneAccountToAnother() {
		balance=50;
		balancePreTransfer=accTwo.getBalance();
		Menu.transferFunds(accOne.getAccNum(), accTwo.getAccNum(), balance);
		assertEquals(balancePreTransfer+balance, accTwo.getBalance(), 1);
	}
	
	@Test
	public void transferFunds_passInvalidSourceAccountNumber_nothingShouldChange() {
		balance=50;
		balancePreTransfer=accTwo.getBalance();
		Menu.transferFunds(accOne.getAccNum()+1, accTwo.getAccNum(), balance);
		assertEquals(balancePreTransfer, accTwo.getBalance(), 1);
	}
	
	@Test
	public void transferFunds_InvalidTargetAccountNumber_nothinShouldChange() {
		balance=50;
		balancePreTransfer=accOne.getBalance();
		Menu.transferFunds(accOne.getAccNum(), accTwo.getAccNum()+1, balance);
		assertEquals(balancePreTransfer, accOne.getBalance(), 1);
	}
	
	@Test
	public void transferFunds_transferorderHigherThanSourceAccountBalance_NothingShouldChange() {
		balance=accOne.getBalance()+1;
		balancePreTransfer=accTwo.getBalance();
		Menu.transferFunds(accOne.getAccNum(), accTwo.getAccNum(), balance);
		assertEquals(balancePreTransfer, accTwo.getBalance(), 1);
	}
	
	@Test
	public void accInfo_validAccountNumber_returnTrue() {
		assertTrue(!(Menu.accInfo(accTwo.getAccNum())));
	}
	
	@Test
	
	public void accInfo_invalidAccountNumber_returnFalse() {
		assertFalse(Menu.accInfo(accOne.getAccNum()+2));
	}

}
