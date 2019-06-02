package View;

import java.io.IOException;
import Controller.Controller;
import Controller.DatabaseFailureException;
import Controller.ItemIdNotFoundInItemRegistryException;
import Model.ItemDTO;
import Model.SaleDTO;
import Model.SaleDirectoryComposite;
import Model.Amount;
import Model.CustomerDTO;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private Controller contr;
    private ErrorMessage errorMsg;
    private LogHandler logger;
    
    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     * @throws IOException 
     *
     */
    public View(Controller contr) throws IOException
    {
        this.contr = contr;
        this.errorMsg = new ErrorMessage();
        contr.addTotalRevenueObserver(new TotalRevenueView());
        this.logger = new LogHandler();
    }
    
    /**
     * Simulates a user input that generates calls to all
     * system operations.
     * @throws DatabaseFailureException 
     * @throws ItemIdNotFoundInItemRegistryException 
     */
    public void runFakeSale() 
    		throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException
    {
    	System.out.println("New sale is started.");
        contr.startNewSale();
        
        contr.addSaleObserver(new SaleItemList());
        int itemID = 1004021;
        recordItem(itemID);
        
        ItemDTO item1 = new ItemDTO(1004021, 2, new Amount(54.99)); 
        SaleDirectoryComposite composite = new SaleDirectoryComposite(); 
        composite.addItem(item1);
        
        SaleDirectoryComposite directory = new SaleDirectoryComposite(); 
        directory.addItem(composite); 
        System.out.println("Total price with no discount: ");
        Amount totalPrice = directory.showTotalPrice();
        System.out.println(totalPrice + "\n");
        System.out.println("Total price with discount: ");
        Amount totalDiscountPrice = directory.showTotalDiscountPrice();
        System.out.println(totalDiscountPrice + "\n");
        
        /*ItemDTO item = new ItemDTO(itemID, 2, new Amount(54.99));
        ItemDTO recordedItem = contr.recordItem(item);
        System.out.println("Recorded item: \n" + recordedItem);*/
        
		//SaleDTO sale = contr.afterItemWasRecorded();
        //System.out.println("\nTotal price:\n" + sale.getTotalPrice());
        
        CustomerDTO customerID = new CustomerDTO(190302);
        contr.recordItemWithDiscount(customerID);
        
        ItemDTO productNoTwo = new ItemDTO(itemID, 2, new Amount(54.99));
		System.out.println("Two items with the same ID " + itemID + " is increased: ");
        int increasedQuantity = contr.increaseAmountSoldItem(productNoTwo);
		System.out.println(increasedQuantity + "st\n");        
        
        contr.endSale();
		System.out.println("\nThe sale is completed.");
		
		System.out.println("\nThe 100kr payment has been initiated.\n");
		contr.initiatePayment(new Amount(100));
		
    }
    
    private void recordItem(int itemID) 
    {
        try 
        {
            int quantity = 2;
            Amount price = new Amount(54.99);
            ItemDTO item = new ItemDTO(itemID, quantity, price);;
            System.out.println("");
            ItemDTO recordedItem = contr.recordItem(item);
            System.out.println("\nResult for item with ID " + itemID +  ": ");
            System.out.println(recordedItem + "\n");
        } 
        catch (ItemIdNotFoundInItemRegistryException ex)
        {
        	fixException("Item with ID " + ex.getID() + " was not found in item registry.", ex);
        }
        catch (DatabaseFailureException ex) 
        {
        	fixException("Failed to connect to database, please try again later.", ex);
		} 
    }
    
    private void fixException(String msg, Exception ex)
    {
    	errorMsg.showErrorMsg(msg);
    	logger.logException(ex);
    }
}