package Integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Model.Amount;
import Model.ItemDTO;
import Model.Sale;

class SaleRegistryTest 
{
	private SaleRegistry instance;
	private Sale sale;
	
	@BeforeEach
	public void setUp() 
	{
		instance = new SaleRegistry();
		sale = new Sale();
	}
	 
	@AfterEach
	public void tearDown() 
	{
		instance = null;
		sale = null;
	}

	@Test
	public void testSetSaleComplete() 
	{
		instance.setSaleComplete(this.sale);
		boolean expectedResult = true;
		boolean actualResult = instance.getCompletedSale();
		assertEquals(expectedResult, actualResult, "A sale was not completed.");
	}
	
	@Test
	void testSaleIsNotUnderway() 
	{
		Sale sale = null;
		instance.setSaleComplete(sale);
		Sale expectedResult = null;
		Sale actualResult = instance.getSale();
		assertEquals(expectedResult, actualResult, "A sale is not underway.");
	}
}
