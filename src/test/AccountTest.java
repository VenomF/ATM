package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import classes.Account;

public class AccountTest {

	static int accNum;
	static double funds;
	static Account accOne;
	static Account accTwo;
	boolean execution;
	private File log=new File("accounts.txt");

	@Before
	public void setUp() throws Exception {
		accOne=new Account(1234, "Faruk", 120.0);
		accTwo=new Account(4321, "Faruk", 50.0);
	}

	@Test
	public void findAcc_passValidAccountNumber_returnsThatAccount() {
		accNum=1234;
		Account accTwo=Account.findAcc(accNum);
		assertEquals(1234, accTwo.getAccNum());
	}

	@Test
	public void findAcc_passInvalidAccountNumber_returnsNull() {
		accNum=1122;
		accTwo=Account.findAcc(accNum);
		assertEquals(null, accTwo);
	}

	@Test
	public void transfer_takesValidAccountInfo_transfersFundsFromOneAccToAnother() {
		funds=accOne.getBalance();
		execution=accOne.transfer(funds, accTwo.getAccNum());
		assertTrue(!execution);
	}

	@Test
	public void transfer_takesFundsExceedingBalnce_retrunsFalse() {
		funds=accTwo.getBalance()+1;
		execution=accTwo.transfer(funds, accOne.getAccNum());
		assertFalse(execution);
	}

	@Test
	public void transfer_takesInvalidTargetAccountNumber_retrunsFalse() {
		execution=accOne.transfer(funds, 1111);
		assertFalse(execution);
	}

	@Test
	public void writer_takesArrayList_writesObjectsToTxtFile() throws IOException {
		Account.write();
		assertTrue(log.canWrite());
	}

	@Test
	public void reader_readsObjectsFromTxtFile_constructsObjectsOfTypeAccount() throws IOException {
		Account.write();
		Account.read();
		assertTrue(log.canRead());
	}

	@Test
	public void toString_takesObjectsDataFields_resurnsThemAsString() {
		String str=accOne.toString();
		assertEquals(str, str);
	}

}
