package View;

/**
 * Is responsible for showing error message on the screen
 * 
 * @author milenavilcinskaite
 *
 */
public class ErrorMessage 
{
	void showErrorMsg(String msg)
	{
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("ERROR: ");
        errorMsg.append(msg);
        errorMsg.append("\n");
        System.out.println(errorMsg);
    }
}
