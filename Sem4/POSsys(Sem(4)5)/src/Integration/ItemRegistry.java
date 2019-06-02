package Integration;

import Model.ItemDTO;
import Model.Amount;
import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseFailureException;
import Controller.ItemIdNotFoundInItemRegistryException;

/**
 * Contains all calls to the data store with items that may be
 * saled.
 */
public class ItemRegistry
{
    private List<ItemDTO> itemList = new ArrayList<>();
    
    ItemRegistry()
    {
        addItems();
    }
    
    /**
	 * Checks if an item with the searched item is in the database
	 * @param itemToSearch The item that is being searched 
	 * @return  a valid item with valid identification number.
	 * An item of <code >itemDTO</code> 
	 * If the item ID is invalid then the method throws an exception. 
     * @throws ItemIdNotFoundInItemRegistryException 
     * @throws DatabaseFailureException The item ID is invalid and therefore cannot connect to the database.
	 */
	public ItemDTO searchItem(ItemDTO itemToSearch) 
			throws ItemIdNotFoundInItemRegistryException, DatabaseFailureException 
	{
		ItemDTO validItem = null;
		for (ItemDTO item : itemList) 
		{
			if(itemToSearch.getID() == 0) 
			{
				throw new DatabaseFailureException("Database failure has occured.");
			}
			
			if(itemToSearch.getID() == item.getID()) 
			{
				validItem = new ItemDTO(item.getID(), item.getQuantity(), item.getPrice());
			}	
			
			else if(validItem == null)
			{
				throw new ItemIdNotFoundInItemRegistryException(itemToSearch.getID());
			}
		}
		
		return validItem;
	}
    
    private void addItems()
    {
    	itemList.add(new ItemDTO(1004021, 2, new Amount(54.99)));
        itemList.add(new ItemDTO(2004202, 1, new Amount(39.99)));
        itemList.add(new ItemDTO(3004204, 8, new Amount(149.99)));
    }
}