package test.java;

import static org.junit.Assert.assertEquals;

public class MainTest
{
	
	@Test
	public void additionTest()
	{
		Main test = new Main(2,3);
		assertEquals(5, test.addition(2,3).size());
	}
	
	
}
