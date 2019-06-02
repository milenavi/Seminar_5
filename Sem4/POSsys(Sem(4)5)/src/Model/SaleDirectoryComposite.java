package Model;

import java.util.ArrayList;
import java.util.List;

public class SaleDirectoryComposite implements SaleStrategy
{
private List<SaleStrategy> itemList = new ArrayList<SaleStrategy>(); 
    
    @Override
    public Amount discountCalculation(Discount discount)  
    { 
    	Amount totalPrice = new Amount(0);
        for(SaleStrategy item : itemList) 
        { 
        	totalPrice = item.discountCalculation(discount);
        }
        
		return totalPrice; 
    } 

    @Override
    public Amount showTotalPrice() 
    {
    	Amount totalPrice = new Amount(0);
    	for(SaleStrategy item : itemList) 
        { 
    		totalPrice = item.showTotalPrice(); 
        }
    	
    	return totalPrice;
    }
    
    @Override
    public Amount showTotalDiscountPrice() 
    {
    	Amount totalDiscountPrice = new Amount(0);
    	for(SaleStrategy item : itemList) 
        { 
    		totalDiscountPrice = item.showTotalDiscountPrice(); 
        }
    	
    	return totalDiscountPrice;
    }
       
    public void addItem(SaleStrategy item) 
    { 
        itemList.add(item); 
    } 
       
    public void removeItem(SaleStrategy item) 
    { 
    	itemList.remove(item); 
    }
}
