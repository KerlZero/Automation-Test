package Pack_Framework_Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestJunit2 {
	
	String message = "Dream";
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test
	public void TestSalutationMessage() {
		System.out.print("Inside TestSalutationMessage() >> ");
		message = "Dream";
		assertEquals(message, messageUtil.printMessage());
	}
	
	
}
