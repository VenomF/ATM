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
	private File log=new File("accounts.txt");
	
	@Before
	public void setUp() throws Exception {
		accOne=new Account(accNum, "Faruk", 120);
		accTwo=new Account(4321, "Faruk", 0);
	}

	@Test
	public void findAcc_passValidAccountNumber_returnsThatAccount() {
		accNum=1234;
		Account accTwo=Account.findAcc(accNum);
		assertEquals(null, accTwo);
	}
	
	@Test
	public void findAcc_passInvalidAccountNumber_returnsNull() {
		accNum=1111;
		accTwo=Account.findAcc(accNum);
		assertEquals(null, accTwo);
	}
	
	@Test
	public void transfer_takesValidAccountInfo_transfersFundsFromOneAccToAnother() {
		funds=20;
		boolean execution=accOne.transfer(funds, accTwo.getAccNum());
		assertEquals(true, execution);
	}
	
	@Test
	public void transfer_takesFundsExceedingBalnce_retrunsFalse() {
		funds=30;
		boolean execution=accTwo.transfer(funds, accOne.getAccNum());
		assertEquals(false, execution);
	}
	
	@Test
	public void transfer_takesInvalidTargetAccountNumber_retrunsFalse() {
		boolean execution=accOne.transfer(funds, 1111);
		assertEquals(false, execution);
	}
	
	@Test
	public void writer_takesArrayList_writesObjectsToTxtFile() throws IOException {
		Account.write();
		assertEquals(true, log.canWrite());
	}
	
	@Test
	public void reader_readsObjectsFromTxtFile_constructsObjectsOfTypeAccount() throws IOException {
		Account.write();
		Account.read();
		assertEquals(true, log.canWrite());
	}
	
	@Test
	public void toString_takesObjectsDataFields_resurnsThemAsString() {
		String str=accOne.toString();
		assertEquals(str, str);
	}

}
