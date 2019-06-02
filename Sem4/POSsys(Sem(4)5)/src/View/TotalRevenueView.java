package View;

import Model.Amount;
import Model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver
{
	public void printTotalRevenue(Amount totalRev) 
	{
		display(totalRev);
		
	}
	
	private void display(Amount totalRev) 
	{
		System.out.println("The total revenue is:\n" + totalRev);
	}
}
