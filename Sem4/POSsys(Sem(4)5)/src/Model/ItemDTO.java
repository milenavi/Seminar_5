package Model;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO implements SaleStrategy
{
    private int id;
    private int quantity;
    private Amount price;
    private Amount totalDiscountPrice;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The amount/quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, int quantity, Amount price)
    {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * Makes the <code>Amount</code>, <code>int</code> and
     * <code>double</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code>, <code>int</code>
     * and <code>double</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + id + ", ");
        builder.append("Quantity: " + quantity + "st , ");
        builder.append("Price: " + price);
        return builder.toString();
    }
    
    public String toStringItemID()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + id);
        return builder.toString();
    }
    
    /**
     * Get the id of an item
     *
     * @return the id of an item
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the price of an item
     *
     * @return the price of an item
     */
    public Amount getPrice()
    {
        return price;
    }

    /**
     * Get the quantity of items
     *
     * @return the quantity of items
     */
    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public Amount showTotalPrice() 
    {
    	double price = this.price.getAmount();
    	double totalPrice = Math.round(price * quantity);
    	Amount totalPriceAmount = new Amount(totalPrice);
    	return totalPriceAmount;
    }
    
    public double getTotal() 
    {
    	double price = this.price.getAmount(); 
    	return price * quantity;
    }

	@Override
	public Amount discountCalculation(Discount discount) 
	{
		int total = 1;
    	Amount totalPrice = new Amount(getTotal());
    	double discountPrice = totalPrice.getAmount();
    	double discountWithTenPercent = total - discount.tenPercentDiscount();
    	double discountWithTwentyPercent = total - discount.twentyPercentDiscount();
    	double discountWithThirtyPercent = total - discount.thirtyPercentDiscount();
    	
        if(discountPrice <= 49.99) 
        {
        	discountPrice = Math.round(calculateDiscountPrice(discountPrice, discountWithTenPercent));
        }
                
        else if(discountPrice <= 149.99) 
        {
        	discountPrice = Math.round(calculateDiscountPrice(discountPrice, discountWithTwentyPercent));
        }
                
        else if(discountPrice >= 149.99) 
        {
        	discountPrice = Math.round(calculateDiscountPrice(discountPrice, discountWithThirtyPercent));
        }
        
        this.totalDiscountPrice = new Amount(discountPrice);
        return this.totalDiscountPrice;
	}
	
	private double calculateDiscountPrice(double discountPrice, double discount) 
    {
    	return discountPrice * discount;
    }
	
	@Override
    public Amount showTotalDiscountPrice() 
    {
    	int total = 1;
    	this.totalDiscountPrice = new Amount(getTotal());
    	double totalDiscountPrice = this.totalDiscountPrice.getAmount();
        if(totalDiscountPrice <= 49.99) 
        {
        	totalDiscountPrice = Math.round(totalDiscountPrice * (total - 0.10));
        }
                
        else if(totalDiscountPrice <= 149.99) 
        {
        	totalDiscountPrice = Math.round(totalDiscountPrice * (total - 0.20));
        }
                
        else if(totalDiscountPrice >= 149.99) 
        {
        	totalDiscountPrice = Math.round(totalDiscountPrice * (total - 0.30));
        }
        
        this.totalDiscountPrice = new Amount(totalDiscountPrice);
        return this.totalDiscountPrice;
    }
}