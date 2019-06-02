package View;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is responsible for the log.
 */
public class LogHandler 
{
	private static final String LOG_FILE_NAME = "POSsys-log.txt";
    private PrintWriter logFile;

    public LogHandler() throws IOException 
    {
    	try {
    		this.logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    	} catch (IOException exc) {
    		System.out.println("ERROR: LogHandler was not created.");
            exc.printStackTrace();
    	}
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exc The exception that shall be logged.
     */
    public void logException(Exception exc)
    {
        StringBuilder logMsg = new StringBuilder();
        logMsg.append("ERROR:");
        logMsg.append(exc.getMessage());
        logMsg.append("\n");
        logFile.println(logMsg);
        exc.printStackTrace(logFile);
    }
}
