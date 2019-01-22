package Pack_Framework_Junit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestJunit1 {
	
	String message = "Dream";
	MessageUtil messageUtil = new MessageUtil(message);
	
	@Test
	public void testPrintMessage() {
		System.out.print("Inside TestPrintMessage() >> ");
		assertEquals(message, messageUtil.printMessage());
	}
}
