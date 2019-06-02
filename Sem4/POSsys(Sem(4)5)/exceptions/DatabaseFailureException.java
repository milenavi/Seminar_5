package Controller;

public class DatabaseFailureException extends Exception
{
	/**
	 * Creates an DatabaseException that informs the user
	 * that an error has occurred during attempted connections
	 * with the database
	 * 
	 * @param msg an string containing the explanation
	 */
	public DatabaseFailureException(String msg) 
	{
		super(msg);
	}
}
