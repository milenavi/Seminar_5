package Model;

import Model.Amount;

/**
 * Represents one specific payment for one specific sale item. The
 * sale item is payed with cash.
 */
public class CashPayment
{
    private Amount paidAmt;
    private Amount totalPrice;
    private Sale sale;
    
    /**
     * Creates a new instance. The customer handed over the
     * specified amount.
     *
     * @param paidAmt The amount of cash that was handed over by the customer.
     *
     */
    public CashPayment(Amount paidAmt)
    {
        this.paidAmt = paidAmt;
    }
    
    /**
     * Calculates the total price of the specified sale that the item is set to.
     *
     * @param paidSale The sale that the item is set to for which the customer is paying.
     *
     */
    public void calculateTotalPrice(Sale paidSale)
    {
        totalPrice = paidSale.getItemForSale().getPrice();
    }
    
    /**
     * @return The total price of the item that is set to sale and that was paid by the customer.
     */
    public Amount getTotalPrice()
    {
        return sale.getChange();
    }
    
    /**
     * @return The amount of change the customer shall have.
     */
    public Amount getChange()
    {
        return paidAmt.minus(totalPrice);
    }
}