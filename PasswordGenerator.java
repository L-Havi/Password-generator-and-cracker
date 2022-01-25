import java.util.Random;

public class PasswordGenerator {
	
	public PasswordGenerator(int passwordLength, char passwordUppercase, char passwordNumbers, char passwordSpecial) {
		
		String passWord = "";
		Random rand = new Random();
		int chooseType = 0;
		int infoLength;
		String[] password = new String[passwordLength];
		for(int i = 0; i < passwordLength; i++) {
			chooseType = rand.nextInt((4 - 1) + 1) + 1;
			if(passwordUppercase == 'n' && chooseType == 2) {
				i--;
			}else {
				if(passwordNumbers == 'n' && chooseType == 3) {
					i--;
				}else {
					if(passwordSpecial == 'n' && chooseType == 4) {
						i--;
					} else {
						password[i] = randomCharacter(chooseType);						
					}
				}
			}
		}
		for(int i = 0; i < passwordLength; i++) {
			passWord = passWord + password[i];
		}
		GUI.createdPassword.setText("New password: " + passWord);
		
		//Array containing data of how secure password is
		String[][] info = 	{
							{"0 seconds","0 seconds","0 seconds","0 seconds"},
							{"0 seconds","0 seconds","0 seconds","0 seconds"},
							{"0 seconds","0 seconds","1 second"," 5 seconds"},
							{"0 seconds","25 secsonds","1 minute","6 minutes"},
							{"5 seconds","22 minutes","1 hour","8 hours"},
							{"2 minutes","19 hours","3 days","3 weeks"},
							{"58 minutes","1 month","7 months","5 years"},
							{"1 day","5 years","41 years","400 years"},
							{"3 weeks","300 years","2 0000 years","34 000 years"},
							{"1 year","16 000 years","100 000 years","2 000 000 years"},
							{"51 years","800 000 years","9 000 000 years","200 000 000 years"},
							{"1 000 years","43 000 000 years","600 000 000 years","15 000 000 000 years"},
							{"34 000 years","2 000 000 000 years","37 000 000 000 years","1 000 000 000 000 years"},
							{"800 000 years","100 000 000 000 years","2 000 000 000 000 years","93 000 000 000 000 years"},
							{"23 000 000 years","6 000 000 000 000 years","100 000 000 000 years","7 000 000 000 000 000 years"}
							};
	
		int complexity = 0;
		if(passwordUppercase == 'y') {
			complexity++;
		}
		if(passwordSpecial == 'y') {
			complexity++;
		}
		if(passwordNumbers == 'y') {
			complexity++;
		}
	
		if(passwordLength <= 4) {
			infoLength = 0;
			GUI.passwordStatistic.setText("\nIt would take about " + info[infoLength][complexity] + " to brute force this password");
		}else if(passwordLength > 4 && passwordLength <= 18){
			infoLength = (passwordLength - 4);
			GUI.passwordStatistic.setText("\nIt would take about " + info[infoLength][complexity] + " to brute force this password");
		} else {
			GUI.passwordStatistic.setText("\nIt would take more than " + info[14][3] + " to brute force this password");
		}
	}

	//Choosing which character to generate
	public static String randomCharacter(int chooseType) {
		String type = "";
		switch(chooseType) {
		case 1:
			type = generateLowercase();
			break;
		case 2:
			type = generateUppercase();
			break;
		case 3:
			type = generateNumber();
			break;
		case 4:
			type = generateSpecial();
			break;
		}
		return type;
	}


	//Generate random special character
	private static String generateSpecial() {
		String[] specialCharacters = {"!","@","#","¤","$","%","€","/","{","(","[",")","]","=","}","?","*","'","^","¨",".",":",",",";","-","_","<",">"};
		Random rand = new Random();
		int choose = rand.nextInt((27 - 1) + 1) + 1;
		String character = specialCharacters[choose - 1];
		return character;
	}

	//Generate random number
	private static String generateNumber() {
		String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
		Random rand = new Random();
		int choose = rand.nextInt((10 - 1) + 1) + 1;
		String number = numbers[choose - 1];
		return number;
	}	

	//Generate random uppercase letter
	private static String generateUppercase() {
		String[] upperCase = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Random rand = new Random();
		int choose = rand.nextInt((26 - 1) + 1) + 1;
		String alphabet = upperCase[choose - 1];
		return alphabet;
	}

	//Generate random lowercase letter
	private static String generateLowercase() {
		Random rand = new Random();
		String[] lowerCase = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int choose = rand.nextInt((26 - 1) + 1) + 1;
		String alphabet = lowerCase[choose - 1];
		return alphabet;
	}
}
