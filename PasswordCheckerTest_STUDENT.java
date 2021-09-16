package Assignments.Fall2020.Assignment1.JGibbs;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * This is your test file.  Complete the following test cases to test your project where they make sense.
 * Include additional test cases if you like.  
 *
 * STUDENT tests for the methods of PasswordChecker
 * @author james gibbs
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwordsArray;
	String password = "Hello";
	String passwordConfirm = "hello";
	String allCaps = "HELLO";
	String withDigit = "Hello6";

	@Before
	public void setUp() throws Exception {
		String[] p = {"354855ab", "IA4cool9U%jeg", "2cold"};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordTooShort() throws LengthException
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidLength("Hello6"));
		}catch(LengthException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertFalse(PasswordCheckerUtility.hasUpperAlpha("beal4"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("BatJ13%"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() throws NoLowerAlphaException
	{

		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha("BatJ13%"));
		}catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
		try {
			assertFalse(PasswordCheckerUtility.hasLowerAlpha("BATJ13%"));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
		
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()throws WeakPasswordException
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("BATj13%"));
		} catch (WeakPasswordException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword("BATj13%yl987"));
		} catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() throws InvalidSequenceException
	{
		try {
			assertFalse(PasswordCheckerUtility.hasSameCharInSequence("BATjjj%yl987"));
		} catch (InvalidSequenceException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() throws NoDigitException
	{

		try {
			assertTrue(PasswordCheckerUtility.hasDigit("BATj13%"));
		} catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() throws LengthException, 
	NoUpperAlphaException, 
	NoLowerAlphaException,
	NoDigitException, 
	NoSpecialCharacterException,
	InvalidSequenceException
	{
		try {
			assertFalse(PasswordCheckerUtility.isValidPassword("abc12"));
		}catch (LengthException e) {
			e.printStackTrace();
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("abc12$T"));
		
		}catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(), 2);
		assertEquals(results.get(0), "354855ab -> The password must contain at least one upper case alphabetic character");
		assertEquals(results.get(1), "2cold -> The password must be at least 6 characters long");
	}
	
}
