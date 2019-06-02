package Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SystemCreator;
import Model.Amount;
import Model.CustomerDTO;
import Model.Discount;
import Model.ItemDTO;
import Model.SaleDTO;
import Model.SaleDirectoryComposite;

class ControllerTest 
{
	 private Controller instance;
	 private RegistryCreator regCreator;
	 private SystemCreator sysCreator;
	 private ByteArrayOutputStream printOut;
	 private PrintStream originalSysOut;
	
	 @BeforeEach
	 public void setUp() 
	 {
		 Printer printer = new Printer();
	     regCreator = new RegistryCreator();
	     sysCreator = new SystemCreator();
	     instance = new Controller(regCreator, sysCreator, printer);
	     
		 printOut = new ByteArrayOutputStream();
		 PrintStream inMemSysOut = new PrintStream (printOut);
		 originalSysOut = System.out;
		 System.setOut (inMemSysOut);

	 }
	 
	 @AfterEach
	 public void tearDown() 
	 {
		 instance = null;
	     regCreator = null;
	     sysCreator = null;
		 System.setOut(originalSysOut);
	     printOut = null;
	 }

	 @Test
	 public void testStartNewSale() 
	 {
		 System.out.println("startNewSale");
	     instance.startNewSale();
	 }
	 
	 @Test
	 public void testRecordItem() 
			 throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException  
	 {
		 instance.startNewSale();
	     int itemID = 1004021;
		 int quantity = 2;
		 Amount price = new Amount(54.99);
		 ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO recordedItem = instance.recordItem(product);
	     String actualResult = recordedItem.toString();
	     String expectedResult = "ID: " + itemID + ", " + "Quantity: " + quantity + "st , " + "Price: " + price;
	     assertEquals("ItemDTO from recordItem is not the same as ItemDTO with expected result.", expectedResult, actualResult);
	 }
	 
	 @Test
	 public void testIncreaseAmountSoldItem() 
			 throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	 {
		 instance.startNewSale();
		 int itemID = 1004021;
	     int quantity = 2;
	     Amount price = new Amount(54.99);
	     ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO soldItem = instance.recordItem(product);
	     int increasedQuantity = instance.increaseAmountSoldItem(soldItem);
		 int actualResult = increasedQuantity;
	     int expectedResult = 4;
	     assertEquals("Wrong print out after calling increaseAmountSoldItem.", expectedResult, actualResult);
	 }
	 
	 @Test
	 public void testRecordItemWithDiscount() 
			 throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	 {
		 instance.startNewSale();
		 int itemID = 1004021;
	     int quantity = 2;
	     Amount price = new Amount(54.99);
	     
	     ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO soldItem = instance.recordItem(product);
	     
	     SaleDirectoryComposite composite = new SaleDirectoryComposite(); 
	     composite.addItem(product);
	        
	     SaleDirectoryComposite directory = new SaleDirectoryComposite(); 
	     directory.addItem(composite); 
	     int totalPrice = (int) directory.showTotalPrice().getAmount();
	     int totalDiscountPrice = (int) directory.showTotalDiscountPrice().getAmount();
	     	     
	     CustomerDTO customerID = new CustomerDTO(190302);
	     instance.recordItemWithDiscount(customerID);
	     
		 int actualResult = totalDiscountPrice;
	     int expectedResult = (int) (totalPrice * (1 - 0.20));
	     assertEquals("Wrong print out after calling recordItemWithDiscount.", expectedResult, actualResult);
	 }
	 
	 
	 @Test
	 public void testInitiatePayment() 
			 throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	 {
		 instance.startNewSale();
		 int itemID = 1004021;
	     int quantity = 2;
	     Amount price = new Amount(54.99);
	     Amount paidAmount = new Amount(100);
	     
	     ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO soldItem = instance.recordItem(product);
	     
	     CustomerDTO customerID = new CustomerDTO(190302);
	     instance.recordItemWithDiscount(customerID);
	     	     
	     int change = (int) instance.initiatePayment(paidAmount).getAmount();
	     
		 int actualResult = change;
	     int expectedResult = 12;
	     assertEquals("Wrong print out after calling initiatePayment.", expectedResult, actualResult);
	 }
	 
}
