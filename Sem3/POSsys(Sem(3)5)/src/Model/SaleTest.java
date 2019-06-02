package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integration.DiscountRegistry;
import Model.Sale;

class SaleTest 
{
	private Sale instance;
	private DiscountRegistry discountRegistry;
	
	@BeforeEach
	public void setUp() 
	{
	    instance = new Sale();
	    discountRegistry = new DiscountRegistry();
	}
	 
	@AfterEach
	public void tearDown() 
	{
		instance = null;
		discountRegistry = null;
	}
	
	@Test
	public void testRecordItem() 
	{
		int itemID = 1004021;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product = new ItemDTO(itemID, quantity, price);
		ItemDTO actualResult = instance.recordItem(product);
	    ItemDTO expectedResult = product;
	    assertEquals(expectedResult, actualResult, "Wrong print out after calling recordItem().");
	}

	@Test
	public void testCalculateTotalPrice() 
	{
	     int itemID = 1004021;
		 int quantity = 2;
		 Amount price = new Amount(54.99);
		 ItemDTO product = new ItemDTO(itemID, quantity, price);
		 instance.calculateTotalPrice(product);
		 double totalPrice = instance.getTotalPrice().getAmount();
		 double actualResult = totalPrice;
	     double expectedResult = Math.round(price.getAmount() * quantity);
	     assertEquals(expectedResult, actualResult, "Wrong print out after calling calculateTotalPrice().");
	}
	
	@Test
	public void testSetIncreasedAmountOfSoldItem() 
	{
		int itemID = 1004021;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product1 = new ItemDTO(itemID, quantity, price);
		ItemDTO product2 = new ItemDTO(itemID, quantity, price);
		instance.recordItem(product1);
		instance.setIncreasedAmountOfSoldItem(product2);
		int increasedQuantity = instance.getIncreasedItemQuantity();
		int actualResult = increasedQuantity;
	    int expectedResult = quantity + quantity;
	    assertEquals(expectedResult, actualResult, "Wrong print out after calling setIncreasedAmountOfSoldItem().");
	}
	
	@Test
	public void testDiscountCalculation()
	{
        CustomerDTO customerID = new CustomerDTO(190302);
		Discount discount = discountRegistry.getDiscount(customerID);
		int itemID = 1004021;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product = new ItemDTO(itemID, quantity, price);
		instance.recordItem(product);
		instance.discountCalculation(discount);
		double totalDiscountPrice = instance.getTotalDiscountPrice().getAmount();
		double actualResult = totalDiscountPrice;
	    double expectedResult = Math.round((price.getAmount() * quantity) * (1 - 0.20));
	    assertEquals(expectedResult, actualResult, "Wrong print out after calling discountCalculation().");
	}
	
	@Test
	public void testInitiatePayment()
	{
        CustomerDTO customerID = new CustomerDTO(190302);
		Discount discount = discountRegistry.getDiscount(customerID);;
		Amount paidAmount = new Amount(100);
		int itemID = 1004021;
		int quantity = 2;
		Amount price = new Amount(54.99);
		ItemDTO product = new ItemDTO(itemID, quantity, price);
		instance.recordItem(product);
		instance.discountCalculation(discount);
		double change = instance.initiatePayment(paidAmount).getAmount();
		double actualResult = change;
	    double expectedResult = 100 - (Math.round((price.getAmount() * quantity) * (1 - 0.20)));
	    assertEquals(expectedResult, actualResult, "Wrong print out after calling initiatePayment().");
		
	}
}
