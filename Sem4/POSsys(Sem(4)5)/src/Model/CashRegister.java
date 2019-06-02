package Model;

import Model.Amount;

/**
 * Represents a cash register. There shall be one instance of
 * this class for each register.
 */
public class CashRegister
{
    private Amount balance;
    
    /**
     * Creates a new instance of a cash register with a balance of zero.
     */
    public CashRegister()
    {
        this.balance = new Amount(0);
    }
    
    /**
     * Gets the value of balance.
     *
     * @return The value of balance.
     */
    public Amount getBalance()
    {
        return balance;
    }
    
    /**
     * Updates the balance with the specified payment.
     *
     * @param payment The amount of cash that will be added to the balance from the cash register.
     */
    public void addPayment(CashPayment payment)
    {
        balance = balance.plus(payment.getTotalPrice());
    }
}