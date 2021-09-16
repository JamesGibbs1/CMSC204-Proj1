package Assignments.Fall2020.Assignment1.JGibbs;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.swing.JOptionPane;

import Assignments.Fall2020.Assignment1.JGibbs.UnmatchedException;
import javafx.scene.control.Alert;

/**
 * Design, implement and test a Java application that will validate passwords.  For this project, a valid password:
1.	is 6 or more characters (long)
2.	10 or more characters is a strong password, however, between 6 and 9 characters is acceptable but considered a weak password
3.	at least 1 numeric character
4.	at least 1 uppercase alphabetic character
5.	at least 1 lowercase alphabetic character
6.	at least 1 special character
7.	no more than 2 of the same character in a sequence

 * @author James Gibbs
 * 
 *
 *
 */
public class PasswordCheckerUtility extends Object{
	ArrayList<String> passwords;
	String password1, password2;
	static int MIN_LENGTH = 6;
	static int MAXWEAK_LENGTH = 9;
	static int STRONG_LENGTH = 10;
	
	
	/**
	 * No argu constructor for password checker
	 */
	PasswordCheckerUtility(){
		passwords = new ArrayList<String>();
	}
	/**
	 * Compare equality of two passwords
	 * @param password -- password string to be checked for
	 * @param passwordConfirm -- passwordConfirm string to be checked against password for
	 * @throws UnmatchedException -- thrown if not same (case sensitive)
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		try {
			if(!password.equals(passwordConfirm)) {
				throw new UnmatchedException();
			}
		}catch(UnmatchedException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	/**
	 * Compare equality of two passwords
	 * @param password -- password string to be checked for
	 * @param passwordConfirm -- passwordConfirm string to be checked against password for
	 * @return true if both same (case sensitive)
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		boolean result = false;
		if(password.equals(passwordConfirm)) {
			result = true;
		}
		
		return result;
		
	}
	/**
	 * Reads a file of passwords and the passwords that failed the check will be added 
	 * to an invalidPasswords with the reason
	 * @param passwords -- list of passwords read from a file
	 * @return invalidPasswords - ArrayList of invalid passwords in the correct format
	 * 
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> status = new ArrayList<>();
		boolean isValid = true;

		if(passwords != null) {
			for(int index = 0; index < passwords.size(); index++) {
				if((passwords.get(index).length() < MIN_LENGTH)){
					status.add(passwords.get(index) + " -> " + "The password must be at least 6 characters long");
				}
				else if(haslowercase(passwords.get(index)) == false) {
					status.add(passwords.get(index) + " -> " + "The password must contain at least one lower case alphabetic character");
				}
				else if(hasuppercase(passwords.get(index)) == false) {
					status.add(passwords.get(index) + " -> " + "The password must contain at least one upper case alphabetic character");
				}
				else if(hasdigit(passwords.get(index)) == false) {
					status.add(passwords.get(index) + " -> " + "The password must contain at least one digit");
				}
				else if(hasspecialchar(passwords.get(index)) == false) {
					status.add(passwords.get(index) + " -> " + "The password must contain at least one special character");
				}
				/*
			else if(hasSameCharInSequence(passwords.get(index)) == false) {
				status.add(passwords.get(index) + " -> " + "The password cannot contain more than two of the same character in sequence");
			}
		}
				 */
			}
		}
		return status;
	}
	
	/**
	 * Weak password length check - Password contains 6 to 9 characters , still considers 
	 * valid, just weak
	 * @param password -- password string to be checked for Sequence requirement
	 * @return true if password contains 6 to 9 characters
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if((password.length() >= 6) && (password.length() <= 9)){
				return true;
		}
		else
			return false;
	}
	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password -- password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException - thrown if does not meet Digit requirement
	 */
	public static boolean hasDigit(String password) throws NoDigitException{
		boolean result = false;

		char[] charArray = password.toCharArray();

		for(int i = 0; i < charArray.length; i++) {
			int digit = 0;
			if(Character.isDigit(charArray[i])) {
				digit++;
				if(digit >= 1) {
					result = true;
				}
			}
		}
		
		
		try {
			if(result == false) {
				throw new NoDigitException();
			}
		}catch(NoDigitException e) {
			e.getMessage();
		}
			return result;
		}
	/**
	 * Checks the password lowercase requirement - Password must contain a lowercase 
	 * alpha character
	 * @param password -- password string to be checked for lowercase requirement
	 * @return true if meet lowercase requirement
	 * @throws NoLowerAlphaException - thrown if does not meet lowercase requirement
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		Pattern pattern = Pattern.compile(".[^a-z]*");
		Matcher matcher = pattern.matcher(password);
		try {
			if(matcher.matches() == false) {
				throw new NoLowerAlphaException();
			}
		}catch(NoLowerAlphaException e) {
			e.getMessage();
		}
		return (!matcher.matches());

	}
	/**
	 * Checks the password Sequence requirement - Password should not contain more than 
	 * 2 of the same character in sequence.compairs one character to the following character to see
	 * if they match. Every time they're similar, the charCount is increased by one.
	 * @param password -- password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException - thrown if does not meet Sequence requirement
	 */
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException{
		boolean result = false;
		int numOfChar = 0;
		
		for (int i = 0; i < password.length() ; i++) {
			int charCount = 0;
			for(int j = i + 1; j < password.length(); j++) {
				if(password.charAt(i) == ( password.charAt(j)))
					charCount++;
			
			}
			if(charCount > numOfChar) {
				numOfChar = charCount;
			}
			if(numOfChar > 2) {
				result = true;
			}
		}
		try {
			if(result == true) {
				throw new InvalidSequenceException();
			}
		}catch(InvalidSequenceException e) {
			e.getMessage();
		}
		return result;
		
	}
	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password -- password string to be checked for SpecialCharacter requirement
	 * @return true if meet SpecialCharacter requirement
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		return (!matcher.matches());

	}
	/**
	 * creates a pattern and matches
	 * @param password being tested
	 * @return true if lower case is present
	 */
	public static boolean haslowercase(String password) {
		Pattern pattern = Pattern.compile(".[^a-z]*");
		Matcher matcher = pattern.matcher(password);
		System.out.println(!matcher.matches());
		return (!matcher.matches());
	}
	/**
	 * creates a pattern and matches
	 * @param password being tested
	 * @return true if upper case is present
	 */
	public static boolean hasuppercase(String password) {
		Pattern pattern = Pattern.compile(".[^A-Z]*");
		Matcher matcher = pattern.matcher(password);
		return (!matcher.matches());
	}
	/**
	 * creates a pattern and matches
	 * @param password being tested
	 * @return true if special character is present
	 */
	public static boolean hasspecialchar(String password) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		return (!matcher.matches());
	}
	/**
	 * creates a pattern and matches
	 * @param password being tested
	 * @return true if digit is present
	 */
	public static boolean hasdigit(String password) {
		Pattern pattern = Pattern.compile(".[^0-9]*");
		Matcher matcher = pattern.matcher(password);
		return (!matcher.matches());
	}
	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha 
	 * character
	 * @param password -- password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
	
		Pattern pattern = Pattern.compile(".[^A-Z]*");
		Matcher matcher = pattern.matcher(password);
		try {
			if(matcher.matches() == false) {
				throw new NoUpperAlphaException();
			}
		}catch(NoUpperAlphaException e) {
			e.getMessage();
		}
		return (!matcher.matches());

	}
	/**
	 * Checks the password length requirement - – The password must be at least 6 characters long
	 * @param password -- password string to be checked for length
	 * @return true if meet min length requirement
	 * @throws LengthException - thrown if does not meet min length requirement
	 */
	public static boolean isValidLength(String password) throws LengthException{
		boolean result = true;
		if(password.length() < MIN_LENGTH) {
			result = false;
		}
		try {
			if(result == false) {
				throw new LengthException();
			}
		}catch(LengthException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() );
		}
		
		return result;
	}
	/**
	 * Return true if valid password (follows all rules from above), returns false 
	 * if an invalid password
	 * @param password -- string to be checked for validity
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException - thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException - thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaExcep - thrown if no lowercase alphabetic
	 * @throws NoDigitException - thrown if no digit
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException - thrown if more than 2 of same character.
	 */
	public static boolean isValidPassword(String password) throws LengthException, 
																NoUpperAlphaException, 
																NoLowerAlphaException,
																NoDigitException, 
																NoSpecialCharacterException,
																InvalidSequenceException
	{
		boolean res = true;
		if(password != null) {
			
			if((password.length() < MIN_LENGTH)){
				res = false;
				throw new LengthException();
			}
			else if(!hasuppercase(password)) {
				res = false;
				throw new NoUpperAlphaException();
			}
			//check for lowercase character
			else if(!haslowercase(password)) {
				res = false;
				throw new NoLowerAlphaException();
			}
			else if(!hasdigit(password)) {
				res = false;
				throw new NoDigitException();
			}
			else if(!hasspecialchar(password)) {
				res = false;
				throw new NoSpecialCharacterException();
			}
			/*else if(hasSameCharInSequence(password)) {
				res = false;
				throw new InvalidSequenceException();
			}
			*/
		}
		return res;
	}
	/**
	 * Checks if password is valid but between 6 -9 characters
	 * @param password -- string to be checked if weak password
	 * @return true if length of password is between 6 and 9 (inclusive).
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		boolean result = false;
		
		if((password.length() >= MIN_LENGTH) && (password.length() < STRONG_LENGTH)) {
				result = true;
		}
		try {
			if(result == true) {
				throw new WeakPasswordException();
			}
		}catch(WeakPasswordException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return result;
		
	}
}

