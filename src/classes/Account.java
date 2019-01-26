package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <b>Account class</b>
 * <p>Account class contains Data fields:
 * -accNum:int
 * -userName:String
 * -balance:double</p>
 * 
 * <p>Class can be instantiated. It is used for construction of bank accounts for simple banking application.</p>
 * 
 * @version 1.6
 * @author Faruk Becirovic (VenomF)
 *
 */

public class Account {
	
	/**
	 * Basic data fields information:
	 * -accNum:int, unique number required for every instance of Account class,
	 * -userName:String, bank account holder's name,
	 * -balance:double, current account status.
	 */
	public static ArrayList<Account> createdAcc = new ArrayList<>();
	private int accNum;
	private String userName;
	private double balance;
	private static File log=new File("accounts.txt");
	
	public Account() {
		/**
		 * Default constructor.
		 */
	}
	
	public Account(int accNum, String userName, double balance) {
		/**
		 * Constructor used for instantiation of Account, after instantiation places all constructed
		 * objects in ArrayList, before construction it verifies that no other account has same account number.
		 */
		if(findAcc(accNum)==null) {
			this.accNum = accNum;
			this.userName = userName;
			this.balance = balance;
			createdAcc.add(this);
		}
		else
			System.out.println("Racun sa tim brojem vec postoji.");
	}
	
	public int getAccNum() {
		return accNum;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public static Account findAcc(int accountNumber) {
		/**
		 * Method used for searching ArrayList in order to find existing Account objects,
		 * returns <b>null</b> if no instances are found.
		 */
		Account acc=null;
		for(int i=0; i<createdAcc.size(); i++)
			if(createdAcc.get(i).accNum==accountNumber)
				acc=createdAcc.get(i);
		
		return acc;
	}
	
	public boolean transfer(double fundsToTransfer, int targetAccNum) {
		/**
		 * Method used for money transfers from this instance to another, it takes desired amount and transfers it
		 * from this account to another specified account. Returns <b>true</b> if transaction is successful
		 * <b>false</b> otherwise.
		 */
		Account targetAccount=findAcc(targetAccNum);
		if(this.balance>=fundsToTransfer && !(targetAccount==null)) {
			this.balance-=fundsToTransfer;
			targetAccount.balance+=fundsToTransfer;
			return true;
		}
		else
			return false;
	}
	
	public static void write() throws IOException {
		/**
		 * Writes all instantiated Account objects from Array list into <b>.txt</b> file.
		 */
		PrintWriter writer=new PrintWriter(log);
		
		for(int i=0; i<createdAcc.size(); i++) 
			writer.println(createdAcc.get(i).accNum + " " + createdAcc.get(i).userName + " " + createdAcc.get(i).balance);
		
		writer.close();
	}
	
	public static void read() throws FileNotFoundException {
		/**
		 * Reads data fields from <b>.txt</b> file in order to instantiate objects with those data fields and
		 * places newly constructed objects in ArrayList.
		 */
		Scanner reader=new Scanner(log);
		
		while(reader.hasNext())
			new Account(reader.nextInt(), reader.next(), reader.nextDouble());
		
		reader.close();
	}

	@Override
	public String toString() {
		/**
		 * Basic <b>toString</b> method, returns <b>String</b> containing basic information
		 * regarding instances of Account.
		 * REturns bank account number, bank account holder's name and current balance.
		 */
			return ("Account \nBroj racuna=" + accNum + "\nIme korisnika=" + userName + "\nIznos na racunu=" + balance);
	}

}
