package classes;

import java.io.IOException;
import java.util.Scanner;

/**
 * <b>Menu class</b>
 * 
 * <p>menu class has one global data field (Scanner), it cannot be instantieted, contains four methods, all static,
 * one main, it is used for simple ATM application.</p>
 * 
 * @version 1.6
 * @author Faruk Becirovic (VenomF)
 *
 */

public class Menu {

	static Scanner input=new Scanner(System.in);

	public static void createAccount() {
		/**
		 * This method is used for creation of new bank accounts. It takes information from user and the passes
		 * that information to constructor from Account class.
		 */
		System.out.println("Unesite broj racuna.");
		int accNum=input.nextInt();
		try {
			System.out.println("Unesite vase ime.");
			String name=input.next();

			System.out.println("Unesite pocetno stanje racuna");
			double startingBalance=input.nextDouble();

			if(startingBalance>=0) {
				Account acc=new Account(accNum, name, startingBalance);
				System.out.println(acc.toString());
				System.out.println("Uspijesno ste krerali racun.");
			}
			else
				System.out.println("Nije moguce kreirati racun sa negativnim iznosom na racunu.");
		}catch(NullPointerException e) {
			System.out.println("Nije moguce kreirati racun.");
		}
		System.out.println();
	}

	public static void transferFunds(int sourceAccNum, int targetAccNum, double transferBalance) {
		/**
		 * Method used for money transfers from one bank account to another.
		 * user inputs source account number target account number and amount for transfer, after that
		 * method calls <b>transfer</b> method from <b>Account</b> class.
		 */

		Account sourceAcc=Account.findAcc(sourceAccNum);

		try {
			if(sourceAcc.transfer(transferBalance, targetAccNum)) {
				System.out.println("<<uspjesno ste prebacili>>" + transferBalance + ">>sa racuna>>" + sourceAccNum + ">>na racun>>" + targetAccNum + ">>");
				System.out.println("Hvala vam na povjerenju...\n");
			}
		}
		catch(NullPointerException f) {
			System.out.println("<<greska prilikom transfera, pokusajte ponovo>>\n");
		}
	}

	public static boolean accInfo(int accNumber) {
		/**
		 * Method that takes bank account from user, calls <b>toString</b> method from <b>Account</b> class and
		 * outputs basic accout information to console.
		 */
		Account userAcc=Account.findAcc(accNumber);

		try{
			System.out.println(userAcc.toString());
			System.out.println("press any key to continue");
			input.next().charAt(0);
			return true;
		}

		catch(NullPointerException g) {
			System.out.println("Takav racun ne postoji.");
			System.out.println("\n");
			return false;

		}
	}

	public static void main(String[] args) throws IOException {
		/**
		 * Main method hold main menu for this simple ATM/Banking application. User chooses witch operation to 
		 * perform, then appropriate methods are called in order to perform desired operations.
		 */
		int odabir=0;

		while(odabir!=4) {
			Account.read();
			System.out.println("Odaberite radnju:");
			System.out.println("1. Kreiraj novi racun \n2. Transfer sredstava \n3. Ispis stanja racuna \n4. Izlaz");
			odabir=input.nextInt();
			System.out.println();

			switch (odabir) {
			case 1 : createAccount();
			break;

			case 2 :
				System.out.println("Unesite broj racuna sa kojeg zelite prebaciti sredstva.");
				int sourceAccNum=input.nextInt();

				System.out.println("Unesite broj racuna na koji zelite prebaciti sredstva.");
				int targetAccNum=input.nextInt();

				System.out.println("Unesite kolicinu koju zelite prebaciti.");
				double transferBalance=input.nextDouble();
				transferFunds(sourceAccNum, targetAccNum, transferBalance);
			break;

			case 3 :
				System.out.println("Unesite broj racuna za koji zelite informacije.");
				if(accInfo(input.nextInt()))
					System.out.println("\n");
			break;

			case 4 : 
				break;

			default : System.out.println("Pogresan unos, molimo pokusajte ponovo.");
			break;
			}
		}

		input.close();
		Account.write();
		System.out.println("THE END");

	}

}
