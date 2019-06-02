package Model;

/**
 * Contains information about one particular customer.
 */
public class CustomerDTO
{
    private int id;
    
    /**
     * Creates a new instance representing a particular customer.
     *
     * @param id The id given to make a discount possible for the customer.
     */
    public CustomerDTO(int id)
    {
        this.id = id;
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getID()
    {
        return id;
    }
}