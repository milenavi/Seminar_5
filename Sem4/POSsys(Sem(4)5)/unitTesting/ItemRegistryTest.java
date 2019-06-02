package Integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.DatabaseFailureException;
import Controller.ItemIdNotFoundInItemRegistryException;
import Model.Amount;
import Model.ItemDTO;

class ItemRegistryTest 
{
	private ItemRegistry instance;
	
	@BeforeEach
	public void setUp() 
	{
		instance = new ItemRegistry();
	}
	 
	@AfterEach
	public void tearDown() 
	{
		instance = null;
	}

	@Test
	void testSearchItem() 
			throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	{
		int itemID = 1004021;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product = new ItemDTO(itemID, quantity, price);
		ItemDTO foundItem = instance.searchItem(product);
		int expectedResult = itemID;
		int actualResult = foundItem.getID();
		assertEquals(expectedResult, actualResult, "An item was not found.");
	}
	
	/*@Test
	void testSearchItemWithInvalidID() 
			throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	{
		int invalidID = 1004655;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product = new ItemDTO(invalidID, quantity, price);
		ItemDTO expectedResult = null;
		ItemDTO actualResult = instance.searchItem(product);
		assertEquals(expectedResult, actualResult, "An invalid item ID was found.");
	}*/


}
