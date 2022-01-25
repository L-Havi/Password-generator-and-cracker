import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	
	JFrame frame;
	JPanel panel;
	Dimension screenSize;
	
	JLabel passwordLengthText;
	static JLabel createdPassword;	
	static JLabel passwordStatistic;
	static JLabel crackedPassword;
	
	char passwordUppercase;
	char passwordNumbers;
	char passwordSpecial;
	char[] charset;
	
	JCheckBox uppercaseLetters;
	JCheckBox numbers;
	JCheckBox specialCharacters;
	
	JButton createPasswordButton;
	JButton bruteForceButton;
	
	JTextField passwordLength;
	
	public GUI() {
		//Creating the graphic user interface
		frame = new JFrame();
		panel = new JPanel();
		
		screenSize = new Dimension(500, 300);
		
		frame.setSize(screenSize);
		frame.setPreferredSize(screenSize);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Password generator");
		frame.pack();
		frame.setVisible(true);
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(null);
		
		createPasswordButton = new JButton("Create password");
		createPasswordButton.setBounds(10, 125, 150, 25);
		createPasswordButton.addActionListener(this);
		bruteForceButton = new JButton("Crack new password");
		bruteForceButton.setBounds(250, 20, 200, 25);
		bruteForceButton.addActionListener(this);
		
		passwordLength = new JTextField(20);
		passwordLength.setBounds(115, 20, 40, 25);
		
		passwordLengthText = new JLabel("Password length:");
		passwordLengthText.setBounds(10, 20, 120, 25);
		createdPassword = new JLabel("New password: ");
		createdPassword.setBounds(10, 170, 250, 25);
		passwordStatistic = new JLabel("It would take about x to brute force this password");
		passwordStatistic.setBounds(10, 195, 500, 25);
		crackedPassword = new JLabel("Password found:");
		crackedPassword.setBounds(250, 50, 400, 25);
		
		uppercaseLetters  = new JCheckBox("Include uppercase letters");
		uppercaseLetters.setBounds(10, 45, 200, 25);
		numbers  = new JCheckBox("Include numbers");
		numbers.setBounds(10, 70, 200, 25);		
		specialCharacters  = new JCheckBox("Include special characters");
		specialCharacters.setBounds(10, 95, 200, 25);
		
		panel.add(createPasswordButton);
		panel.add(passwordLengthText);
		panel.add(passwordLength);
		panel.add(uppercaseLetters);
		panel.add(numbers);
		panel.add(passwordStatistic);
		panel.add(specialCharacters);
		panel.add(createdPassword);
		panel.add(bruteForceButton);
		panel.add(crackedPassword);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		//Create password
		if(o == createPasswordButton) {
			
			//Getting input
			int length = Integer.parseInt(passwordLength.getText());
			boolean uppercase = uppercaseLetters.isSelected();
			boolean number = numbers.isSelected();
			boolean special = specialCharacters.isSelected();
			
			//Converting checkboxes to char
			if (uppercase == false) {
				passwordUppercase = 'n';
			} else {
				passwordUppercase = 'y';				
			}
			if (number == false) {
				passwordNumbers = 'n';
			} else {
				passwordNumbers = 'y';				
			}
			if (special == false) {
				passwordSpecial = 'n';
			} else {
				passwordSpecial = 'y';				
			}
			
			new PasswordGenerator(length, passwordUppercase ,passwordNumbers, passwordSpecial);
			
			
		//Brute force
		} else if(o == bruteForceButton) {
			//Getting password from text
			String rawPasswordText = createdPassword.getText();
			String passWord = rawPasswordText.substring(14);
			
			//Choosing the correct charset
			if(passwordUppercase == 'n' && passwordNumbers == 'n' && passwordSpecial == 'n') {
				charset = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			}else if(passwordUppercase == 'n' && passwordNumbers == 'n' && passwordSpecial == 'y') {
				charset = "abcdefghijklmnopqrstuvwxyz!@#¤$%€/{([)]=}?*'^¨.:,;-_<>".toCharArray();
			}else if(passwordUppercase == 'y' && passwordNumbers == 'n' && passwordSpecial == 'n') {
				charset = "aabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			}else if(passwordUppercase == 'n' && passwordNumbers == 'y' && passwordSpecial == 'n') {
				charset = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
			}else if(passwordUppercase == 'y' && passwordNumbers == 'n' && passwordSpecial == 'y') {
				charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#¤$%€/{([)]=}?*'^¨.:,;-_<>".toCharArray();
			}else if(passwordUppercase == 'y' && passwordNumbers == 'y' && passwordSpecial == 'n') {
				charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
			}else if(passwordUppercase == 'n' && passwordNumbers == 'y' && passwordSpecial == 'y') {
				charset = "abcdefghijklmnopqrstuvwxyz0123456789!@#¤$%€/{([)]=}?*'^¨.:,;-_<>".toCharArray();
			}else {
				charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#¤$%€/{([)]=}?*'^¨.:,;-_<>".toCharArray();
			}
			
			PasswordCracker.BruteForce(passWord, charset);
		}
	}
}
