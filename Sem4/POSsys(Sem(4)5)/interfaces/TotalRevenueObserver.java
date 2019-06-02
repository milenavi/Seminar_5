package Model;

/**
 * TotalRevenueObserver is an interface for observed objects.
 */
public interface TotalRevenueObserver 
{
	/**
	 * The observed object will call this on the observing object whenever a
	 * observable event occurs.
	 * @param paidAmount Paid amount by the customer of the observed object
	 */
	void printTotalRevenue(Amount paidAmount);
}
