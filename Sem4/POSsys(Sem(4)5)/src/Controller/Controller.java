package Controller;

import Integration.ItemRegistry;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SaleRegistry;
import Integration.DiscountRegistry;
import Integration.SystemCreator;
import Integration.InventorySystem;
import Integration.AccountingSystem;
import Model.CashRegister;
import Model.Amount;
import Model.CashPayment;
import Model.Sale;
import Model.SaleDTO;
import Model.SaleObserver;
import Model.TotalRevenueObserver;
import Model.Receipt;
import Model.ItemDTO;
import Model.CustomerDTO;
import Model.Discount;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private Sale sale;
    private final DiscountRegistry discountRegistry;
    private final ItemRegistry itemRegistry;
    private RegistryCreator regCreator;
    private final SaleRegistry saleRegistry;
    private final Printer printer;
    private final CashRegister cashRegister;
    private final AccountingSystem accountingSystem;
    private final InventorySystem inventorySystem;
    private ItemDTO item;
    private Discount discount;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     *
     * @param sysCreator Used to get all classes that handle external system calls.
     *
     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator, SystemCreator sysCreator, Printer printer)
    {
        this.itemRegistry = regCreator.getItemRegistry();
        this.saleRegistry = regCreator.getSaleRegistry();
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.accountingSystem = sysCreator.getAccountingSystem();
        this.inventorySystem = sysCreator.getInventorySystem();
    }
    
    /**
     *  Initiates a new sale.
     */
    public void startNewSale()
    {
        this.sale = new Sale();
    }
    
    /**
     * Get the information of the recorded item..
     *
     * @param item The item where the customer is buying and the cashier is recording.
     *
     * @return The information of the recorded item.
     * @throws DatabaseFailureException 
     * @throws ItemIdNotFoundInItemRegistryException 
     */
    public ItemDTO recordItem(ItemDTO item) 
    		throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException
    {
    	ItemDTO validItem = itemRegistry.searchItem(item);
    	ItemDTO recordedItem = sale.recordItem(validItem);
    	
    	return recordedItem;
    }
    
    /**
     * Increases amount of sold items. After calling this method, the amount of items
     * will increase. This method also
     * permanently saves information about the current sale.
     *
     * @param soldItem The sold item that will be increased.
     */
    public int increaseAmountSoldItem(ItemDTO soldItem)
    {
    	saleRegistry.saveSale(sale);
    	int increasedQuantity = sale.setIncreasedAmountOfSoldItem(soldItem);
    	return increasedQuantity;
    }
    

    /**
	 * indicateAllItemsRegistered returns an instance 
	 * of SaleDTO when all items are registered
	 * @return an SaleDTO instance
	 */
	public SaleDTO afterItemWasRecorded() 
	{
		return new SaleDTO(this.sale);
	}
    
    /**
     * Records an item with requested discount from the customer.
     * After calling this method, the item
     * will be discounted. This method also
     * permanently saves information about the current discount.
     *
     * @param customer The specified customer that is buying an item with requested discount.
     * @throws java.lang.ArgumentException
     */
    public void recordItemWithDiscount(CustomerDTO customer) 
    {
    	Discount discount = discountRegistry.getDiscount(customer);
    	sale.discountCalculation(discount);
        discountRegistry.saveDiscount(discount);
    }
    
    /**
     * Ends a long started new sale. After calling this method, the sale
     * will be ended by the cashier. This method also
     * updates the external systems such as <code>inventorySystem</code> and <code>accountingSystem</code>.
     */
    public void endSale()
    {
        saleRegistry.setSaleComplete(sale);
        accountingSystem.updateAccountingSystem(sale);
        inventorySystem.updateInventorySystem();
    }
    
    /**
     * Initiates a payment with the paid amount.
     * Also, the external systems will be updated.
     * This method handles sale item payment and at the end it will calculate change and
     * print the receipt.
     *
     * @param paidAmount The amount of cash paid by the customer.
     */
    public Amount initiatePayment(Amount paidAmount)
    {
    	Amount change = sale.initiatePayment(paidAmount);
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        
        return change;
    }
    
    public void addTotalRevenueObserver(TotalRevenueObserver obs) 
    {
    	accountingSystem.addObserver(obs);
	}
    
    public void addSaleObserver(SaleObserver obs)
    {
        sale.addObserver(obs);
    }
}