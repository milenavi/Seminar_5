package Model;

import Model.Sale;
import Model.ItemDTO;
import java.util.ArrayList;
import java.util.List;
import Model.Discount;
import Model.Receipt;
import Integration.Printer;

/**
 * Represents one particular sale transaction, where one
 * particular item is purchased by one particular customer.
 */
public class Sale
{
	private List<ItemDTO> itemList = new ArrayList<>();
    private Amount change;
    private Amount totalPrice = new Amount(0);
	private Amount totalDiscountPrice = new Amount(0);
    private ItemDTO itemForSale;
    private Receipt receipt;
    private int increasedItemQuantity;
        
    /**
     * Creates a new instance, representing a sale made by
     * the cashier in order to set the item for sale.
     *
     */
    public Sale()
    {
    }
    
    
    public ItemDTO recordItem(ItemDTO itemToRecord) 
    {
    	itemList.add(itemToRecord);
    	calculateTotalPrice(itemToRecord);    	
    	
        return itemToRecord;
    }
    
    public void calculateTotalPrice(ItemDTO itemToRecord)
    {
    	this.itemForSale = itemToRecord;
    	double itemPrice = itemToRecord.getPrice().getAmount();
    	int itemQuantity = itemToRecord.getQuantity();
    	double totalPrice = itemPrice * itemQuantity;
    	double roundedTotalPrice = roundTotalPrice(totalPrice);
    	this.totalPrice.add(roundedTotalPrice);
    }
    
    private double roundTotalPrice(double totalPrice) 
    {
    	return Math.round(totalPrice);
    }
    
    /**
     * Sets increased amount of a sold item. After calling this method,
     * the sold item will be incremented in the POS system.
     *
     * @param recordedItem The item that is recorded in the POS system.
     */
    public int setIncreasedAmountOfSoldItem(ItemDTO otherRecordedItem)
    {
    	for(ItemDTO item : itemList) 
    	{
    		int itemQuantity = item.getQuantity();
    		int otherItemQuantity = otherRecordedItem.getQuantity();
    		int firstRecordedItemID = item.getID();
        	int otherRecordedItemID = otherRecordedItem.getID();
    		if(firstRecordedItemID == otherRecordedItemID)
        	{
    			this.increasedItemQuantity = itemQuantity + otherItemQuantity;
            }
    	}
    	return this.increasedItemQuantity;
    }
    
    public int getIncreasedItemQuantity()
    {
    	return this.increasedItemQuantity;
    }
    
    public Amount discountCalculation(Discount discount) 
    {
    	int total = 1;
    	Amount totalPrice = this.totalPrice;
    	double discountPrice = totalPrice.getAmount();
    	double discountWithTenPercent = total - discount.tenPercentDiscount();
    	double discountWithTwentyPercent = total - discount.twentyPercentDiscount();
    	double discountWithThirtyPercent = total - discount.thirtyPercentDiscount();
    	
        if(discountPrice <= 49.99) 
        {
        	discountPrice = calculateDiscountPrice(discountPrice, discountWithTenPercent);
        }
                
        else if(discountPrice <= 149.99) 
        {
        	discountPrice = calculateDiscountPrice(discountPrice, discountWithTwentyPercent);
        }
                
        else if(discountPrice >= 149.99) 
        {
        	discountPrice = calculateDiscountPrice(discountPrice, discountWithThirtyPercent);
        }
        
        double roundedTotalDiscountPrice = roundTotalPrice(discountPrice);
        this.totalDiscountPrice.add(roundedTotalDiscountPrice);

        return this.totalDiscountPrice;
    }
    
    public double calculateDiscountPrice(double discountPrice, double discount) 
    {
    	return discountPrice * discount;
    }
    
    public Amount getTotalPrice() 
    {
    	return this.totalPrice;
    }
    
    public Amount getTotalDiscountPrice() 
    {
    	return this.totalDiscountPrice;
    }
    
    /**
     * Sets the current item for sale.
     *
     * @param itemForSale The item that is currently for sale.
     */
    public void setItemForSale(ItemDTO itemForSale)
    {
        this.itemForSale = itemForSale;
    }
    
    /**
     * @return The item that is currently for sale.
     */
    public ItemDTO getItemForSale()
    {
        return this.itemForSale;
    }
    
    /**
     * This sale item is paid using the specified payment.
     *
     * @param payment The payment used to pay this sale item.
     */
    public Amount initiatePayment(Amount paidAmount)
    {
    	this.change = paidAmount.minus(this.totalDiscountPrice);
        
    	return this.change;
    }
    
    public Amount getChange() 
    {
    	return this.change;
    }
    
    /**
     * Prints a receipt for the current sale item on the
     * specified printer.
     * @param printer The printer that prints the receipt.
     */
    public void printReceipt(Printer printer)
    {
        receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
    
    /**
     * Returns a receipt for the current sale item.
     * @return receipt for the purchased item.
     */
    public Receipt getReceipt()
    {
        return new Receipt(this);
    }
    
    public Sale getSale() 
	{
		return this;
	}
}