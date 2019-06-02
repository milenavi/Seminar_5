package View;

import java.util.ArrayList;
import java.util.List;

import Model.SaleObserver;

public class SaleItemList implements SaleObserver
{
	private List<String> itemList;

    public SaleItemList()
    {
    	itemList = new ArrayList<>();
    }

    /**
     * Adds an item to itemList.
     * @param item An information of an item that should be added to the list.
     */
    @Override
    public void addItem(String item) 
    {
    	itemList.add(item);
    	System.out.println("Sale observer: The item was added.");
    }
}
