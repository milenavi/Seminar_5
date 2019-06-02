package Model;

import Model.CustomerDTO;

public class Discount
{
    private CustomerDTO customer;
    private double tenPercentDiscount = 0.10;
    private double twentyPercentDiscount = 0.20;
    private double thirtyPercentDiscount = 0.30;
    
    /**
     * Creates a new instance, representing a discount requested by
     * the specified customer.
     *
     * @param customer The requesting/purchasing customer.
     */
    public Discount(CustomerDTO customer)
    {
        this.customer = customer;
    }
    
    public double tenPercentDiscount() 
    {
 	   return tenPercentDiscount;
    }
    
    public double twentyPercentDiscount() 
    {
 	   return twentyPercentDiscount;
    }
    
    public double thirtyPercentDiscount() 
    {
 	   return thirtyPercentDiscount;
    }
}

