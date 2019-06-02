package Model;

/**
 * Implements the algorithm of the discount calculation
 * and other types of calculations.
 * 
 * @author milenavilcinskaite
 *
 */
public interface SaleStrategy 
{
	/**
     * Returns the total price of the item.
     */
	Amount showTotalPrice();
    
    /**
     * Shows the total discounted price of the item.
     */
	Amount showTotalDiscountPrice();
    
    /**
     * Calculates the total discount price of the item.
     */
    Amount discountCalculation(Discount discount);
}
