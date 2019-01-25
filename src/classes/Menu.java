package classes;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

	static Scanner input=new Scanner(System.in);

	public static void createAccount() {
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

	public static void transferFunds() {

		System.out.println("Unesite broj racuna sa kojeg zelite prebaciti sredstva.");
		int sourceAccNum=input.nextInt();

		System.out.println("Unesite broj racuna na koji zelite prebaciti sredstva.");
		int targetAccNum=input.nextInt();

		System.out.println("Unesite kolicinu koju zelite prebaciti.");
		double transferBalance=input.nextDouble();

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

	public static void accInfo() {
		System.out.println("Unesite broj racuna za koji zelite informacije.");
		int accNumber=input.nextInt();
		Account userAcc=Account.findAcc(accNumber);

		try{
			System.out.println(userAcc.toString());
			System.out.println("press any key to continue");
			input.next().charAt(0);
		}

		catch(NullPointerException g) {
			System.out.println("Takav racun ne postoji.");
		}
		System.out.println("\n");
	}

	public static void main(String[] args) throws IOException {
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

			case 2 : transferFunds();
			break;

			case 3 : accInfo();
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
