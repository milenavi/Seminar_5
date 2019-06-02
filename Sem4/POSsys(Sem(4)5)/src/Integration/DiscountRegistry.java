package Integration;

import Model.Discount;
import Model.CustomerDTO;

/**
 * Contains all calls to the data store with performed discounts.
 */
public class DiscountRegistry
{
    private Discount discount;
    
    /**
     * Saves the specified discount permanently.
     *
     * @param discount The discount that will be saved.
     */
    public void saveDiscount(Discount discount)
    {
        this.discount = discount;
    }
    
   /**
    *
    * @param customer
    * 
    * @return discount New discount is returned
    */
   public Discount getDiscount(CustomerDTO customer)
		   throws IllegalArgumentException
   {
	   if (customer.getID() < 0)
       throw new IllegalArgumentException("Given customer ID not found." + " Customer ID: " + customer.getID());
       
       return discount = new Discount(customer);
   }
}