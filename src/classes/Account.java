package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	public static ArrayList<Account> createdAcc = new ArrayList<>();
	private int accNum;
	private String userName;
	private double balance;
	private static File log=new File("accounts.txt");
	
	public Account(int accNum, String userName, double balance) {
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
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public static Account findAcc(int accountNumber) {
		Account acc=null;
		for(int i=0; i<createdAcc.size(); i++)
			if(createdAcc.get(i).accNum==accountNumber)
				acc=createdAcc.get(i);
		
		return acc;
	}
	
	public boolean transfer(double fundsToTransfer, int targetAccNum) {
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
		PrintWriter writer=new PrintWriter(log);
		
		for(int i=0; i<createdAcc.size(); i++) 
			writer.println(createdAcc.get(i).accNum + " " + createdAcc.get(i).userName + " " + createdAcc.get(i).balance);
		
		writer.close();
	}
	
	public static void read() throws FileNotFoundException {
		Scanner reader=new Scanner(log);
		
		while(reader.hasNext())
			new Account(reader.nextInt(), reader.next(), reader.nextDouble());
		
		reader.close();
	}

	@Override
	public String toString() {
			return ("Account \nBroj racuna=" + accNum + "\nIme korisnika=" + userName + "\nIznos na racunu=" + balance);
	}

}
