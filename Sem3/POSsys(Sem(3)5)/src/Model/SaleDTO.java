package Model;

public class SaleDTO 
{
	private Amount totalPrice;
	private Amount totalDiscountPrice;
	private Amount change;
	
	/**
	 * Creates an DTO of the ongoing sale to be used by other packages
	 * @param sale an instance of the Sale-class, contains info about the current sale
	 */
	public SaleDTO(Sale sale) 
	{
		this.totalPrice = sale.getTotalPrice();
		this.totalDiscountPrice = sale.getTotalDiscountPrice();
		this.change = sale.getChange();
	}

	public Amount getTotalPrice() 
	{
		return totalPrice;
	}
	
	public Amount getTotalDiscountPrice() 
    {
    	return this.totalDiscountPrice;
    }
	
	public Amount getChange() 
    {
    	return this.change;
    }
}
