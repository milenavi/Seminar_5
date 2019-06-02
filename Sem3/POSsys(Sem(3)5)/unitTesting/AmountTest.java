package Model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AmountTest 
{
	@Test
	void testMinusAmount() 
	{
		double amount1 = 100;
		double amount2 = 50;
		Amount amountTest1 = new Amount (amount1);
		Amount amountTest2 = new Amount (amount2);
		Amount subtractedAmount = amountTest1.minus(amountTest2);
		double expectedResult = amount1 - amount2;
		double actualResult = subtractedAmount.getAmount();
		assertEquals(expectedResult, actualResult, "Wrong subtraction result is returned.");
	}
	
	@Test
	void testPlusAmount() 
	{
		double amount1 = 100;
		double amount2 = 50;
		Amount amountTest1 = new Amount (amount1);
		Amount amountTest2 = new Amount (amount2);
		Amount subtractedAmount = amountTest1.plus(amountTest2);
		double expectedResult = amount1 + amount2;
		double actualResult = subtractedAmount.getAmount();
		assertEquals(expectedResult, actualResult, "Wrong addition result is returned.");
	}
	
	@Test
	void testAddDoubleAmount() 
	{
		double amount1 = 100;
		double amount2 = 50;
		Amount amountTest1 = new Amount (amount1);
		amountTest1.add(amount2);
		double expectedResult = amount1 + amount2;
		double actualResult = amountTest1.getAmount();
		assertEquals(expectedResult, actualResult, "Wrong addition result is returned.");
	}
	
	@Test
	void testAddNegativeAmount() 
	{
		double amount1 = 100;
		double amount2 = -50;
		Amount amountTest1 = new Amount (amount1);
		amountTest1.add(amount2);
		double expectedResult = amount1 + amount2;
		double actualResult = amountTest1.getAmount();
		assertEquals(expectedResult, actualResult, "Wrong addition result is returned.");
	}
	
	@Test
	void testMinusNegativeAmount() 
	{
		double amount1 = 100;
		double amount2 = -50;
		Amount amountTest1 = new Amount (amount1);
		Amount amountTest2 = new Amount (amount2);
		Amount subtractedAmount = amountTest1.minus(amountTest2);
		double expectedResult = amount1 - amount2;
		double actualResult = subtractedAmount.getAmount();
		assertEquals(expectedResult, actualResult, "Wrong subtraction result is returned.");
	}
	
	@Test
	void testResultBecomesZeroAmount() 
	{
		double amount1 = 100;
		double amount2 = -100;
		Amount amountTest1 = new Amount (amount1);
		amountTest1.add(amount2);
		double expectedResult = amount1 + amount2;
		double actualResult = amountTest1.getAmount();
		assertEquals (expectedResult, actualResult, "Wrong addition result is returned.");
	}
    
    @Test
    void testMinusNegativeZeroResult() 
    {
        double amount1 = 100;
        double amount2 = -100;
        Amount amountTest1 = new Amount (amount1);
        Amount amountTest2 = new Amount (amount2);
        double expectedResult = amount1 - amount2;
        double actualResult = amountTest1.minus(amountTest2).getAmount();
        assertEquals (expectedResult, actualResult, "Wrong subtraction result is returned.");
    }
    
    @Test
    public void testToStringNegativeAmount() 
    {
        int amount1 = -100;
        Amount amountTest1 = new Amount(amount1);
        String expectedResult = amount1 + "kr";
        String actualResult = amountTest1.toString();
        assertTrue("Wrong message is returned by toString().", expectedResult.equals(actualResult));
    }
    
    @Test
    public void testToStringZeroAmount() 
    {
        int amount1 = 0;
        Amount amountTest1 = new Amount(amount1);
        String expectedResult = amount1 + "kr";
        String actualResult = amountTest1.toString();
        assertTrue("Wrong message is returned by toString().", expectedResult.equals(actualResult));
    }

    @Test
    public void testToStringPositiveAmount() 
    {
        int amount1 = 100;
        Amount amountTest1 = new Amount(amount1);
        String expectedResult = amount1 + "kr";
        String actualResult = amountTest1.toString();
        assertTrue("Wrong message is returned by toString().", expectedResult.equals(actualResult));
    }
}
