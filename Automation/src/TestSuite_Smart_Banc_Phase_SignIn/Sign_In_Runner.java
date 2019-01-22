package TestSuite_Smart_Banc_Phase_SignIn;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import TestSuite_Smart_Banc_Phase_SignIn.CT_TestSuit_Phase_SignIn;

public class Sign_In_Runner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(CT_TestSuit_Phase_SignIn.class);
		
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      
	      System.out.println(result.wasSuccessful());

	}

}
