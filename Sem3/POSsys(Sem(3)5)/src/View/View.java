package View;

import Controller.Controller;
import Model.ItemDTO;
import Model.SaleDTO;
import Model.Amount;
import Model.CustomerDTO;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private Controller contr;
    
    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     *
     */
    public View(Controller contr)
    {
        this.contr = contr;
    }
    
    /**
     * Simulates a user input that generates calls to all
     * system operations.
     */
    public void runFakeSale()
    {
    	System.out.println("New sale is started.\n");
        contr.startNewSale();
                
        int itemID = 1004021;
        ItemDTO item = new ItemDTO(itemID, 2, new Amount(54.99));
        ItemDTO recordedItem = contr.recordItem(item);
        System.out.println("Recorded item: \n" + recordedItem);
        
		SaleDTO sale = contr.afterItemWasRecorded();
        System.out.println("\nTotal price:\n" + sale.getTotalPrice());
        
        CustomerDTO customerID = new CustomerDTO(190302);
        contr.recordItemWithDiscount(customerID);
        System.out.println("\nTotal price with discount:\n" + sale.getTotalDiscountPrice());
        
        ItemDTO productNoTwo = new ItemDTO(itemID, 2, new Amount(54.99));
		System.out.println("\nTwo items with the same ID " + itemID + " is increased: ");
        int increasedQuantity = contr.increaseAmountSoldItem(productNoTwo);
		System.out.println(increasedQuantity + "st");        
        
        contr.endSale();
		System.out.println("\nThe sale is completed.");
		
		System.out.println("\nThe 100kr payment has been initiated.\n");
		contr.initiatePayment(new Amount(100));
		
    }
}