package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Integration.DiscountRegistry;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SystemCreator;


class ReceiptTest 
{
	private Sale sale;
	private Receipt receipt;
	private DiscountRegistry discountRegistry;
	
	@BeforeEach
	public void setUp() 
	{
		sale = new Sale();
	    discountRegistry = new DiscountRegistry();
	}
	 
	@AfterEach
	public void tearDown() 
	{
		sale = null;
		receipt = null;
		discountRegistry = null;
	}

	/*@Test
	public void testCreateReceiptString() 
	{
		CustomerDTO customerID = new CustomerDTO(190302);
		Discount discount = discountRegistry.getDiscount(customerID);
		Amount price = new Amount(54.99);
		int totalPrice = 88;
		int change = 12;
        int quantity = 2;
        int itemID = 1004021;
        ItemDTO item = new ItemDTO(itemID, quantity, price);
        sale.recordItem(item);
        sale.setIncreasedAmountOfSoldItem(item);
        sale.discountCalculation(discount);
        sale.initiatePayment(new Amount(100));
        receipt = new Receipt(sale);
        LocalDateTime saleTime = LocalDateTime.now();
        String expectedResult = 
        		"\nItem Sale Receipt\n" +
                "\nSale time:" + saleTime.toString() +
                "\n\nThe Sold Item:\n" + item.toString() +
                "\n\nTotal price:\n" + totalPrice + "kr" + 
                "\n\nChange after payment:\n" + change + "kr";
        String actualResult = receipt.createReceiptString();
        assertEquals("Receipt string is not equal to the actual receipt string.", expectedResult, actualResult);	
	}*/

}
