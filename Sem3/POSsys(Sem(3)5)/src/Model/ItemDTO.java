package Model;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO
{
    private int id;
    private int quantity;
    private Amount price;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The amount/quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, int quantity, Amount price)
    {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * Makes the <code>Amount</code>, <code>int</code> and
     * <code>double</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code>, <code>int</code>
     * and <code>double</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + id + ", ");
        builder.append("Quantity: " + quantity + "st , ");
        builder.append("Price: " + price);
        return builder.toString();
    }
    
    public String toStringItemID()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + id);
        return builder.toString();
    }
    
    /**
     * Get the id of an item
     *
     * @return the id of an item
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the price of an item
     *
     * @return the price of an item
     */
    public Amount getPrice()
    {
        return price;
    }

    /**
     * Get the quantity of items
     *
     * @return the quantity of items
     */
    public int getQuantity()
    {
        return quantity;
    }
}